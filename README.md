thanks for trying my project!

What it does
The base version of nope-reader is a program that creates html pages to read manga chapters. It was made for pc (linux and windows compatible) and it was made for ease of use. 
This version is a slightly modified version of the original nope-reader that was made to solve the problem of css and icon files not being used when viewing an html file on ios. It will also workon pc, but its not ideal as it requires manually copying files. 


How to use it
1)download the chapters you want from any site (by default, only .img images will be added to the html file).
2)add the chapter folders in a directory with the name of the manga. then move your new folder onto the ./manga folder that I created. The relative path has to look something like this: ./manga/the_manga's_name/
2.5)I will provide an example of a manga.
3)move the ressources folder in the ./manga/the_manga's_name/ folder.
4)if you didn't installed it already, install java jdk. if you don't if you have it, type " java --version " in  a command prompt.
4.1)run the program.
   4.2)On linux, type " java src/Main.java ". alternatively, you can run the shell script that I provided.
   4.3)For the oher windows plebs, you can run the .bat file.
4.4)*IMPORTANT* If you are running the program for the first time on windows or have a bunch of .htm files (NOT html), you might need to change the line 54 of /src/Main.java . If that is the case, copy this text (without the quotes) ".filter((String s) -> s.endsWith(".htm")).collect(Collectors.toList());" in line 45, replacing the line that was previously there. After that, yo can undo the change you made if you plan on reusing the program in the same folder. 

If you are interested, I am also working on a node.js version of this program and it should work better than this. (it might not be finished by the time you check it) 