##### Maakodu ilmajaama kasutamine (algus) #####



* Konfiguratsiooni ja graafide leht - http://tomcat-madisuibo.193b.starter-ca-central-1.openshiftapps.com/nutikodu/interface.jsp

* Arduino saab postitada sensori poolt loetud andmeid siia - http://tomcat-madisuibo.193b.starter-ca-central-1.openshiftapps.com/nutikodu/sensorServlet
  * Peaks postitama kujul - temperature={$tempVar}&lighting=${lightVar}, nt. käsitsi läbi brauseri saab proovida http://tomcat-madisuibo.193b.starter-ca-central-1.openshiftapps.com/nutikodu/sensorServlet?temperature=15&lighting=92

* Arduino saab lugeda küttekeha ja valgusti lülitite andmeid siit - http://tomcat-madisuibo.193b.starter-ca-central-1.openshiftapps.com/nutikodu/controlsServlet
  * Lehel on kaks booleani, esimene on küttekeha lüliti, teine valgusti lüliti (true - lülitab sisse, false - lülitab välja)



##### Maakodu ilmajaama kasutamine (lõpp) #####
