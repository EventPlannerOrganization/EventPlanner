Feature: Update Service Type

  Background: testing
    Given Data Base already filled
   Scenario Outline: Change The List Of Services For Service And Package Provider To Single Service
     When Service Provider or Package Provider Who's Want to Change His Service Type To Single Service Is <ServiceProvider>
     And The new Service is <ServiceType> <ServiceDescription> <ServicePrice>
     Then The Service Will Update Successfully
     Examples:
     |ServiceProvider|ServiceType|ServiceDescription|ServicePrice|
     |"Ibrahim160"   |"DJ"        |"GoodService"    |"1000"      |
     |   "baha02"    |"VENUE"     |"Best Place "    |"2000"     |
     Scenario Outline: Change Service For Service And Package Provider To MultiServices
   When Service Provider or Package Provider Who's Want to Change His Service Type To Multi Service Is <ServiceProvider>
       And The First Service is  <ServiceType1> <ServiceDescription1> <ServicePrice1>
       And The Second Service is <ServiceType2> <ServiceDescription2> <ServicePrice2>
       And The Third Service is <ServiceType3> <ServiceDescription3> <ServicePrice3>
       Then The List Of Services Will Update Successfully
       Examples:

         | ServiceProvider | ServiceType1 | ServiceDescription1 | ServicePrice1 | ServiceType2 | ServiceDescription2 | ServicePrice2 | ServiceType3       | ServiceDescription3     | ServicePrice3 |
         | "Ibrahim160"    | "DJ"         | "GoodService"       | "1000"        | "PHOTOGRAPHY" | "BestPhotos"        | "2000"        | "SECURITY"         | " Making ur event safe" | "900"         |
         | "baha02"        | "VENUE"      | "Best Place "       | "2000"        | "CATERING"   | "Best Food"         | "2000"        | "DECOR_AND_DESIGN" | " Making ur event cool" | "1500"        |
