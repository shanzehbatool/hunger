#voice2.py
import pyttsx3  
import speech_recognition as sr

import sys
import pymongo
import pprint
import requests
import urllib.parse
from bson.son import SON
from pymongo import MongoClient, GEOSPHERE
from geopy.geocoders import Nominatim


r = sr.Recognizer()  
engine = pyttsx3.init()  

with sr.Microphone() as source:
    text = "Please tell your name"  
    engine.say(text)
    engine.runAndWait()
    print(text)
    audio = r.listen(source)
    try:
        name = r.recognize_google(audio)
        print("You said : {}".format(name))
    except:
        print("Sorry could not understand what you said. Please repeat")

with sr.Microphone() as source:
    text = "Please tell your location"  
    engine.say(text)  
    engine.runAndWait()
    print(text)
    audio = r.listen(source)
    try:
        location = r.recognize_google(audio)
        l="{}".format(location)
        print("You said :",l)
    except:
        print("Sorry could not understand what you said. Please repeat")

import nltk
import spacy
  
# essential entity models downloads
"""nltk.downloader.download('maxent_ne_chunker')
nltk.downloader.download('words')
nltk.downloader.download('treebank')
nltk.downloader.download('maxent_treebank_pos_tagger')
nltk.downloader.download('punkt')
nltk.download('averaged_perceptron_tagger')"""
import locationtagger

# extracting entities.
place_entity = locationtagger.find_locations(text = l)

# getting all region cities
print("The region cities in text : ")
print(place_entity.region_cities)

# getting all other regions
print("All other regions in text : ")
print(place_entity.other_regions)

# getting all other entities
print("All other entities in text : ")
print(place_entity.other)

#getting the cluster 
cluster = pymongo.MongoClient("mongodb+srv://pranjal:litchi09@mycluster.raql5.mongodb.net/myFirstDatabase?retryWrites=true&w=majority",
                              tls=True,
                             tlsAllowInvalidCertificates=True)

#setting up the Nomanatim API to convert latitude and longitude to string address
geolocator = Nominatim(user_agent="geoapiExercises")

#Infirmation of the poor person
Nm = name
j=0
St=""
Ar=""
Ct=""
for i in place_entity.region_cities:
    if(j==0):
        St=i
    j+=1   
Ct = place_entity.region_cities.get(St)[0]
j=0
for i in place_entity.other:
    if(j==0):
        Ar=i
    j+=1

if (St.find('State of') != -1):
	St=St[9:]
#St = "Madhya Pradesh"
#Ct = "Indore"
Ar = "Nanda Nagar"
#parsing the latitude and longitude of the string address
address = Ar + ", " + Ct + ", " + St + ", India" 
print(address)
url = 'https://nominatim.openstreetmap.org/search/' + urllib.parse.quote(address) +'?format=json'

response = requests.get(url).json()
if (len(response) > 0):
    #print(response[0]["lat"])
    #print(response[0]["lon"])

    #setting the latitudes and longitudes to float type and storing them in variables 
    long = float(response[0]["lon"])
    lat = float(response[0]["lat"])

    #getting the database from the cluster and storing the reference of the restaurants collection
    db = cluster["hunger"]
    restaurants = db["restaurants"]
    
    #print(type(long))
    md = 10000 #max distance to be searched for a restaurant nearby (in metres)
    db.restaurants.create_index([("Location", GEOSPHERE)])
    #db.users.create_index([("Location", GEOSPHERE)])
 
    #setting the criteria to get the list of restaurants
    rest = ""
    query = {"Location": {"$nearSphere": {"$maxDistance": md ,"$geometry":{"type":"Point","coordinates":[long, lat]}}},"Availability" : "Yes"}
    
    #selecting the restaurants which fits the minimum distance and availability criteria
    doc = restaurants.find_one(query)
    #print(doc['Location']['coordinates'][0])
    
    rest = doc['Name']
    
    rest_Lat = str(doc['Location']['coordinates'][1]) #restuarant's Latitude
    rest_Long = str(doc['Location']['coordinates'][0]) #restaurant's Longitude
    location = geolocator.reverse(rest_Lat + "," + rest_Long) #converting the address into list of strings
    

    rest += ", " + str(location[0])  #concatenation of elements of address into full address
    
    #checking if any retaurant is found 
    if(rest != ""):
        print(rest)
        r = sr.Recognizer()  
        engine = pyttsx3.init()  
        with sr.Microphone() as source:
            engine.say(rest)
            engine.runAndWait()

    else:
        print("No restaurent is available")
    quit()
else : 
    print("Incorrect address. Please try with a different or correct address")