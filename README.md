# GraphQLDemo-Server
GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data. GraphQL provides a complete and understandable description of the data in your API, gives clients the power to ask for exactly what they need and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools.

**#GraphQL over REST, Why?**

**Over fetching:** Over-fetching is fetching too much data, aka there is data in the response you don't use.

**Under fetching:** Under-fetching is not having enough data with a call to an endpoint, leading you to call the second endpoint.
In both cases, they are performances issues: you either use more bandwidth than you should, or you are making more HTTP requests that you should.

In a perfect world, these problems would never arise; you would have exactly the right endpoints to give exactly the right data to your products.

These problems often appear when you scale and iterate on your products. The data you use on your pages often change, and the cost to maintain a separate endpoint with exactly the right data for each component becomes too much.

So you end up with a compromise between not having too many endpoints and having the endpoints fit the need best. This will lead to over-fetching in some cases (the endpoint will provide more data that you need for your specific component), and under-fetching in others (you will need to call a second endpoint).

So GraphQL fixes this problem because it allows access to an arbitrary set of data exposed by the server. You specifically specify what you need and will get this data, and only this data, in one trip to the server.

**#How to do GraphQL with SpringBoot and MongoDB?**

1. Create spring boot application (https://start.spring.io) and open it in your favorite IDE, mostly IntelliJ
2. Setup your MongoDB (https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x)
3. Add below lines to your POM.xml and sync. you can use the latest version.
```
<!-- Graph QL -->
<dependency>
 <groupId>com.graphql-java</groupId>
 <artifactId>graphiql-spring-boot-starter</artifactId>
 <version>4.2.0</version>
</dependency>

<dependency>
 <groupId>com.graphql-java</groupId>
 <artifactId>graphql-spring-boot-starter</artifactId>
 <version>4.2.0</version>
</dependency>

<dependency>
 <groupId>com.graphql-java</groupId>
 <artifactId>graphql-java-tools</artifactId>
 <version>5.1.0</version>
</dependency>
```

4. Let's start writing code.
 

**#Terminologies:**

**1.Query**

It's like a GET request, Every time we want to access the data for reading, We make use of Queries.
We can specify as many queries as we want in our GraphQL Application and we can call those queries as needed.,
We can pass ```optional``` or  ```required``` parameters to the query as well, And we can use those parameters internally to filter and return the correct data.
Anyways, Query doesn't fetch the data, It acts as a proxy, It processes the incoming GraphQL request and calls the 'resolve' method and passes along all the parameters, Its the job of resolve method to fetch the data from any source and return it back to the query.
The query then checks the data for correct field types and returns it back to the client.

**2. Mutation**

Just like queries, Mutation is used whenever we want to manipulate the data, This is used for Create, Update and Delete operations.
Every time we want to add/manipulate a record, We call GraphQL Mutation and pass all the arguments/parameters., The mutation then calls the 'resolve'method and passes along all the data it receives.
Here we do wherever we want with the data and however we want to do it, once done we return the response back to the mutation, which in turn will return the response back to the client. (after validating its field types)

**3. Subscription**

In its simplest terms, Subscriptions are like queries, but every time the data changes the query is run and the new, response is sent back to all connected clients.
This is relatively new, But a step forward in the direction of real-time updates.

**4. Resolver**

We use resolve for fetching and manipulating the data. How we want to do it is left entirely on us.
We can fetch the data from Database, API, File, or any data source. GraphQL doesn't restrict us.
To maintain strong typing and intuitive design, it is common to represent GraphQL types with equivalent Java classes, and fields with methods. graphql-java-tools defines two types of classes: data classes, which model the domain and are usually simple POJOs, and resolvers, that model the queries and mutations and contain the resolver functions.

**5. Types**

GraphQL comes with a set of default scalar types out of the box:
Int: A signed 32‐bit integer.
Float: A signed double-precision floating-point value.
String: A UTF‐8 character sequence.
Boolean: true/ false
ID: The ID scalar type represents a unique identifier, often used to re-fetch an object or as the key for a cache. The 'ID' type is serialized in the same way as a String; however, defining it as a 'ID' signifies that it is not intended to be human‐readable.

**6. Schema**

It's here where we connect everything together.
Here we create a 'RootQuery' (name can be anything) and here we specify all the queries we have written.
Similarly, We create a 'RootMutation' and specify all the mutations we have written.
Finally, we return a GraphQLSchema with this 'query' and  'mutation' object.
 
 **emessage.graphqls**
```
type EMessage {
    id: ID
    subject: String
    description: String
    category: String
    read: Boolean
    sentDateTime:String
    sender: Sender
}

extend type Query {
    allMessage: [EMessage]
    message(id: ID): EMessage
}

extend type Mutation {
    createMessage(subject: String, description: String,
                  category: String, senderEmail:String): String
}
```
**sender.graphqls**
```
type Sender {
    email:String
    firstName:String
    lastName:String
}

type Query {
    allSender: [Sender]
}

type Mutation {
    createSender(email: String, firstName: String,
                 lastName: String): String
}
```

**How to test?**

http://localhost:8080/graphiql

```
query {
  allMessage {
    id
    subject
    description
    sender {
      firstName
      lastName
    }
  }
}
```

```
query {
 message(id:"5b3658d1cf56c459c6ce07fa") {
     id
    subject
  }
}
```

```
mutation {
createMessage(subject: "GraphQL new Message",
    		description: "This is new description 1",
   			category:"Social")
}
```


**#References:**

https://graphql.org

https://www.howtographql.com

https://www.apollographql.com
