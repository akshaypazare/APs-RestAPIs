# APs-RestAPIs
Akshay Pazare (Java Developer) developed this RestAPIs for performing the following tasks that are mentioned in the COMPETENCY SCREENING(CS) of Samta sustainable digitalization

Submitted by: Akshay Pazare

Instructions: Please test the APIs using postman

*first of all configure your database with the application

1.Fetch 5 random questions from the API endpoint: https://jservice.io/api/random.
   Store them in database along with all the details.
   
   To perform the above functionality please enter the API- "yourHost:8080/RestAPIs/getQuestion" in Get request and send request
 
2.Create a get API /play – This API should return question along with id. {“question_id”:
  <question id>, “question”: <question text>}
  
  To perform this after fetching all the questions, Please enter the API- "yourHost:8080/RestAPIs/play" in the Get request and send         request
  
3.Create a post API /next – This API should take payload {“question_id”: <id>, “answer”:
  <answer>}. Response of the API should return {“correct_answer”: <correct answer to
  question>, “next_question”: {“question_id”: <question id>, “question”: <question
  text>}}
    
  To peform this provide the required payload in json format in postman and enter the API- "yourHost:8080/RestAPIs/next" in the Post       request and send request
  
 
