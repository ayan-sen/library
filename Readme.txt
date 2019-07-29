Database used : MongoDB

API Details:

1. Create book data
URL: POST /book 
Sample Payload:
{
    "title": "SQL book",
    "author": "Parker",
    "publishedDate": "2018-02-22",
    "isbn": "981483029127",
    "reviews": [
      {
        "reviewerName": "John",
        "content": "The content is really good",
        "rating": 4.0,
        "publishedDate": "2018-02-28"
      },
      {
        "reviewerName": "Mike",
        "content": "Very informative",
        "rating": 5.0
      }
    ]
  }
Respose: 
{
    "status": "success"
}

2. Fetch book data based on title
URL: /books/title/{title}
Sample Response:
[
    {
        "id": "5d3de7860d7bcd1848b1a963",
        "title": "Web application book",
        "author": "Ron",
        "publishedDate": "2017-02-01",
        "isbn": "35870923712",
        "reviews": [
            {
                "reviewerName": "Leonard",
                "content": "There are some topic that has been deprecated",
                "rating": 2
            }
        ]
    }
]

