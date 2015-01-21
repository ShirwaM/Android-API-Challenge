# Guidebook Challenge 
### Problem
Build an Android app that will display data from Guidebook servers. 

### Approach
I didn't have a lot of time to build it better, but basically the app pulls data from the Guidebook server and then displays it. 
I created a custom client class that handles gathering the data from the server. All requests and processing is done off the main 
UI thread to prevent the app from hanging or crashing. When data is being pulled or processed, a small loading bar is displayed to 
give the user the indication that the app is doing work and that it isn't frozen. Error handling is also done within the client class
to prevent the app from crashing. 

To load the images, I used a library called [Universal Image Loader](https://github.com/nostra13/Android-Universal-Image-Loader). This library is simple and uses modern caching techniques to 
improve load time and processing time. To display the name and date, I used the new RecyclerView layout that was introduced with 
Android Lolipop. It uses the ViewHolder pattern to improve speed when scrolling by keeping a reference to the approapriate views for 
fast access. When the a list item is clicked, it opens up a broswer with the associated link obtained from the JSON data. 

If I had more time, I would of focused more on the UI design and overall layout. It definielty could use a lot more work. I would also
clean up the code and make it more efficent. There were a few places where I cut corners like instead of displaying all the guides and then 
reloading everytime the app is opened, we can store the data in a SQL database and when a guide expires, we can clear it from the database. The app 
would pull only once a day instead of multiple times in one day. 

###Home Screen
![alt tag](https://raw.githubusercontent.com/ShirwaM/guidebook-challenge/master/app/screenshots/pic1.png)
Displays a list of guides that are clickable. When a guide is clicked, it opens the link that corresponds to that Guide. 
###Final Remarks 
This project was interesting and was a good excersise. I tried to limit the number of times I used a library. I could of used [Ion](https://github.com/koush/ion) which would of handled both 
JSON processing and image processing, but I thought it would be better to implement the processing from the ground up to save on space. 

###APK - http://bit.ly/181VY2D
