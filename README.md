# UI-UX
	- Provides UI/UX support for common features
	- Realtime LiveData UI (MVVM) [`Pending`]

## Features:
	• OTP Login UI
	
	
# Login UI
• Integrate in your gradle build (app.build) :
			
```
dependencies {
    ...
    implementation 'com.github.kayhan-swarup:UI-UX:1.0.3'
    ...
}
```

			
	
•And finally add the line below in the project's gradle (e.g: project-name.build):

```

    allprojects {
        repositories {
            ...        
            maven { url 'https://jitpack.io' }
            ...
        }
    }

```


# Code Example:

• If user is not logged in just copy the code below:


```java

LoginHandler.getInstance()
	.startLogin(v.getContext(), new LoginListener() {
	@Override
	public void onLoggedIn(FirebaseUser firebaseUser) {
	    if(firebaseUser!=null){
		try {
		    /*
		    *   Handle after successfully logged in
		    */

		    Toast.makeText(getBaseContext(),"Logged in: "+firebaseUser.getPhoneNumber(),Toast.LENGTH_LONG).show();
		} catch (Exception e) {
		    L.e(e);
		}
	    }else{
		/*
		*   Failed to log in
		*/


		Toast.makeText(getBaseContext(),"Login failed",Toast.LENGTH_LONG).show();
	    }
	}
	});
```

# Example screens:

<img src="https://user-images.githubusercontent.com/7688524/100517792-867c4780-31b7-11eb-9ffe-af3c2be0a10d.png" />
	

		
		
		
