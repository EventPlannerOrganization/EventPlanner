Feature: Sign Up

  Scenario Outline:Sign Up User successfully
    When Authentication is <username> <password>
    And Name is <firstName> <middleName> <lastName>
    And ContactInfo is <email> <phoneNumber>
    And Address is <country> <city>
    Then User will sign up seccessfully
    Examples:
      | username          | password        | firstName  | middleName | lastName    | email                      | phoneNumber  | country     | city       |
      | 'BahaAlawneh1'     | 'Bahaaa123$'    | 'Bahaa'     | 'khaled'   | 'Alawneh'   | 'bahaalawneh@gmail.com'    | '0594371093' | 'palestine' | 'jenin'    |
      | 'naserabusafieh1'  | 'Naser1230$$'   | 'Naserr'    | 'mohammed' | 'abusafieh' | 'naserabusafieh@gmail.com' | '0593878342' | 'palestine' | 'nablus'   |
      | 'mohammmedshadid1' | 'Mohammed4321$' | 'Mohammmed' | 'Munir'    | 'Shadid'    | 'Mohammedshadid@gmail.com' | '0593456982' | 'palestine' | 'tulkarem' |

  Scenario Outline:Sign Up Service Provider successfully
    When Authentication is <username> <password>
    And Name is <firstName> <middleName> <lastName>
    And ContactInfo is <email> <phoneNumber>
    And Address is <country> <city>
    And Service is <serviceType> <servicePrice> <serviceDiscription>
    Then Service Provider will sign up seccessfully
    Examples:
      | username        | password       | firstName | middleName | lastName   | email                   | phoneNumber  | country     | city       | serviceType | servicePrice | serviceDiscription         |
      | 'nassarharashi' | 'Nassar2135$$' | 'Nassassr'  | 'Mohammed' | 'Harashi'  | 'Nassar@gmail.com'      | '0599288345' | 'palestine' | 'Tulkarem' | 'DJ'        | '90.0'       | 'dj for parties'           |
      | 'hayasamarah'   | 'Hayaa12345$$' | 'Hayaa'    | 'Mohammed' | 'Samaanah' | 'Haya@gmail.com'        | '0594365892' | 'palestine' | 'Nablus'   | 'Security'  | '90.0'       | 'party security'           |
      | 'BahaAlawneh'   | 'Bahaaa123$'   | 'Bahaaa'    | 'khaled'   | 'Alawneh'  | 'bahaalawneh@gmail.com' | '0594371093' | 'palestine' | 'jenin'    | 'Cleaning'  | '90.0'       | 'cleannin the party place' |

  Scenario Outline: Sign Up Service Provider fail and Weak Password Exception Will be thrown
    When Authentication is <username> <password>
    And Name is <firstName> <middleName> <lastName>
    And ContactInfo is <email> <phoneNumber>
    And Address is <country> <city>
    And Service is <serviceType> <servicePrice> <serviceDiscription>
    Then Service Provider sign up fails because of weak password
    Examples:
      | username        | password     | firstName | middleName | lastName  | email                   | phoneNumber  | country     | city       | serviceType | servicePrice | serviceDiscription         |
      | 'nassarharashi' | 'nassar2135' | 'Nassar'  | 'Mohammed' | 'Harashi' | 'Nassar@gmail.com'      | '0599288345' | 'palestine' | 'Tulkarem' | 'DJ'        | '90.0'       | 'dj for parties'           |
      | 'hayasamarah'   | 'hayaa12345' | 'Haya'    | 'Mohammed' | 'Samarah' | 'Haya@gmail.com'        | '0594365892' | 'palestine' | 'Nablus'   | 'Security'  | '90.0'       | 'party security'           |
      | 'BahaAlawneh'   | 'bahaaa123'  | 'Baha'    | 'khaled'   | 'Alawneh' | 'bahaalawneh@gmail.com' | '0594371093' | 'palestine' | 'jenin'    | 'Cleaning'  | '90.0'       | 'cleannin the party place' |

  Scenario Outline:Sign Up User fail and Weak Password Exception will be thrown
    When Authentication is <username> <password>
    And Name is <firstName> <middleName> <lastName>
    And ContactInfo is <email> <phoneNumber>
    And Address is <country> <city>
    Then User sign up fails because of weak password
    Examples:
      | username          | password     | firstName  | middleName | lastName    | email                      | phoneNumber  | country     | city       |
      | 'BahaAlawneh'     | 'Bahaaa12'   | 'Baha'     | 'khaled'   | 'Alawneh'   | 'bahaalawneh@gmail.com'    | '0594371093' | 'palestine' | 'jenin'    |
      | 'naserabusafieh'  | 'Naser1'     | 'Naser'    | 'mohammed' | 'abusafieh' | 'naserabusafieh@gmail.com' | '0593878342' | 'palestine' | 'nablus'   |
      | 'mohammmedshadid' | 'Mohammed43' | 'Mohammed' | 'Munir'    | 'Shadid'    | 'Mohammedshadid@gmail.com' | '0593456982' | 'palestine' | 'tulkarem' |

