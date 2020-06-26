package Chatting;


public class Chatting_TransactionBuilder {
	   
    private Chatting_Transaction t;
    
   public Chatting_TransactionBuilder(Chatting_Transaction t){
       this.t = t;
    }

   public Chatting_TransactionBuilder id(String id){
      t.setID(id);
      return this;
   }

   public Chatting_TransactionBuilder password(String password){
      t.setPassword(password);
      return this;
   }

   public Chatting_TransactionBuilder cCharac(String cCharac){
      t.setcCharac(cCharac);
      return this;
   }

   public Chatting_TransactionBuilder cGender(String cGender){
      t.setcGender(cGender);
      return this;
   }

   public Chatting_Transaction transaction(){
      return t;
   }
}