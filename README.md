# CircleProgress
 
 Simple Circle Progress
 
-All attrributes
 
  <com.sertac.sertac.circleprogress.CircleProgress  
 
		android:layout_width="match_parent"
		android:layout_height="100dp"
        	app:progress_color="@color/colorAccent"
        	app:progress_line_width="15dp"
        	app:background_progress_color="@color/colorPrimaryDark"
        	app:background_progress_line_width="6dp"
        	app:text_color="@color/colorPrimary"
        	app:text_size="10sp"
        	android:max="25"
        	app:progress="2"/>

-Gradle

	allprojects {
		repositories {
		
		 maven { url 'https://jitpack.io' }
		}
	}
              
  implementation 'com.github.sertacokan:CircleProgress:-SNAPSHOT'
	
  
  -Maven
  
  	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>	
	<dependency>
	    <groupId>com.github.sertacokan</groupId>
	    <artifactId>CircleProgress</artifactId>
	    <version>-SNAPSHOT</version>
	</dependency>
   
  -SBT
  
    resolvers += "jitpack" at "https://jitpack.io"
    	
	libraryDependencies += "com.github.sertacokan" % "CircleProgress" % "-SNAPSHOT"	

-Leiningen

    :repositories [["jitpack" "https://jitpack.io"]]
        	
	 :dependencies [[com.github.sertacokan/CircleProgress "-SNAPSHOT"]]	

                                    
    
    
