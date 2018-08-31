## BITTREX JAVA WRAPPER


##### Quick Start


```JAVA
 
public class Quickstart extends BittrexAPI{

    Quickstart(){
        super("key", "secretKey");

        set(getMarkets()).forEach("LogoUrl", (e) -> {
           ...
        });

        set(getMarketHistory("BTC-LTC")).forEach("Price", (e) -> {
           ...
        });

        set(getTicker("BTC-LTC")).get("Bid" , (e) -> {
           ...
        });
        
    }

    public static void main(String[] args){
        new Quickstart();
    }

}
```


```JAVA
 
public class Quickstart extends BittrexAPI{

    Quickstart(){
        super("key", "secretKey");

        ArrayList<String> logoUrls = set(getMarkets()).getAll("LogoUrl");
        for(String logo : logoUrls){
           ...
        }   
        
        ArrayList<String> priceList = set(getMarketHistory("BTC-LTC")).getAll("Price");
        for(String price : priceList){
           ...
        }
        
        String bid = set(getTicker("BTC-LTC")).get("Bid");
        System.out.println(bid);

    }

    public static void main(String[] args){
        new Quickstart();
    }

}
```

PUBLIC 
```JAVA
    //returns a string
    getMarkets();
    getCurrencies();
    getMarketSummaries();
    getMarketSummary("MARKET NAME");
    getTicker("MARKET NAME");
    getOrderBook("MARKET NAME");
    getMarketHistory("MARKET NAME");
```
ACCOUNT 
```JAVA
    //returns a string
    getBalances();
    getBalance("MARKET NAME");
    getOrderHistory("MARKET NAME");
    getOrder("UUID");
    getWithdrawalHistory("MARKET NAME");
    getDepositAddress("MARKET NAME");
    Withdraw("MARKET NAME", "QUANTITY", "ADDRESS");
```
MARKET 
```JAVA
    //returns a string
    buyLimit("MARKET NAME" , "QUANTITY" , "RATE");
    sellLimit("MARKET NAME" , "QUANTITY" , "RATE");
    cancelOrder("UUID");
    getOpenOrders("MARKET NAME");
```

![](example.gif)


