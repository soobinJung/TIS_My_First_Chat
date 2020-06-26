package Chatting;

public class Chatting_Transaction {
	   
    private String ID;
    private String password;
    private String cCharac;
    private String cGender;
   
   public String getID() {
      return ID;
   }
   public void setID(String iD) {
      ID = iD;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getcCharac() {
      
      return cCharac;
   }
   public void setcCharac(String cCharac) {
      this.cCharac = cCharac;
   }
   public String getcGender() {
      return cGender;
   }
   public void setcGender(String cGender) {
      this.cGender = cGender;
   }
    
}