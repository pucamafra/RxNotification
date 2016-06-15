<img width="100" src="./img/RxNotification_icon.png" />

RxNotification
===============

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxNotification-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3546)

Easy way to register, remove and manage notifications using RxJava

Target platforms
---

- API level 14 or later

Latest version
---

- Version 2.0.0  (JUN. 15, 2016)

Usage
---

In order to use the library, there are 3 different options:

**1. Gradle dependency** (recommended)

 - 	Add the following to your `build.gradle`:
 ```gradle
repositories {
	    jcenter()
}

dependencies {
	   compile 'com.marlonmafra.rxnotification:rx-notification:2.0.0'
}
```

**2. Maven**
- Add the following to your `pom.xml`:

 ```xml
<dependency>
        <groupId>com.marlonmafra.rxnotification</groupId>
        <artifactId>rx-notification</artifactId>
        <version>2.0.0</version>
        <type>pom</type>
</dependency>
```

**3. Ivy**

 ```xml
<dependency org='com.marlonmafra.rxnotification' name='rx-notification' rev='2.0.0'/>
```

# Sample usage

Getting a token

```java
  RxNotification.getToken(this.gcmRegId)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String token) {
                      //Send token to back-end
                    }
                });
```

or

```java
  RxNotification.getToken()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String token) {
                      //Send token to back-end
                    }
                });
```
Removing a token

```java
RxNotification.removeToken(this.gcmRegId)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void mVoid) {
                    }
                });
```

Removing instance. All tokens will be removed

```java
RxNotification.removeInstance(this)
.               subscribeOn(Schedulers.io())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void mVoid) {
                    }
                });
```

Verify whether the Google play Service is update/installed or not

```java
        try {
            RxNotificationUtil.verifyGooglePlayService(this);
            //Register token
        } catch (DeviceUnsupportedException e) {
            //Do Whatever you want
        } catch (GooglePlayServicesOutDatedException e) {
            //Do Whatever you want
        } catch (GooglePlayServicesNotInstalledException e) {
            //Do Whatever you want
        } catch (UnknownErrorException e) {
            //Do Whatever you want
        }
```

According with the new library from Firebase Cloud Message you only need to put these code on AndroidManifest.xml

```xml

    <application ....>

        <service
            android:name="com.marlonmafra.rxnotification.sample.GCMService"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>

    </application>
}
```

For more details, take a look on sample module.


License
---

	Copyright (c) 2016 Marlon Mafra

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

