Feature: Admin Register New Service Provider

  Scenario Outline:Admin Register Service Provider successfully
    When Authentication is <username> <password>
    And Name is <firstName> <middleName> <lastName>
    And ContactInfo is <email> <phoneNumber>
    And Address is <country> <city>
    And Service is <serviceType> <servicePrice> <serviceDiscription>
    Then Service Provider will sign up seccessfully
    Examples:
      | username        | password       | firstName | middleName | lastName   | email  | phoneNumber  | country     | city       | serviceType | servicePrice | serviceDiscription         |
      | 'ebaareshah' | 'Ebaa123@' | 'ebaa'  | 'muntaser' | 'reshah'  | 'ebaa@gmail.com' | '0599288345' | 'palestine' | 'nablus' |    'DJ'        | '90.0'       | 'dj for parties'           |
