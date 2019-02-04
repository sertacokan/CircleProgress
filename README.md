# CircleProgress

## Screenshots

![First Usage](https://github.com/sertacokan/CircleProgress/blob/master/app/src/main/res/drawable/Screenshot1.png?raw=true)
![Second Usage](https://github.com/sertacokan/CircleProgress/blob/master/app/src/main/res/drawable/Screenshot2.png?raw=true)
![Third Usage](https://github.com/sertacokan/CircleProgress/blob/master/app/src/main/res/drawable/Screenshot3.png?raw=true)

## All attrributes

 ``` Java
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
```

## Usage

##### Gradle
```
	allprojects {
		repositories {
		
		 maven { url 'https://jitpack.io' }
		}
	}
              
  implementation 'com.github.sertacokan:CircleProgress:-SNAPSHOT'
  
  ```  
  
##### Maven
  ```
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

```
##### SBT
  ```
    resolvers += "jitpack" at "https://jitpack.io"
    	
	libraryDependencies += "com.github.sertacokan" % "CircleProgress" % "-SNAPSHOT"	

```
##### Leiningen
```
    :repositories [["jitpack" "https://jitpack.io"]]
        	
    :dependencies [[com.github.sertacokan/CircleProgress "-SNAPSHOT"]]	
```    
## License

Copyright 2018 Sertac Okan Celik.

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.    
    
