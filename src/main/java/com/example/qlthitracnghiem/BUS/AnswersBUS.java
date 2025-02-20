
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.AnswersDAO;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anhhu
 */
public class AnswersBUS {
    
    public AnswersBUS(){}
    
    public AnswersDAO answersDAO = new AnswersDAO();
    
    
    
             public boolean create(AnswersDTO answer) {
            try{
            return answersDAO.create(answer);
            } catch (SQLException e){
            return false;
            }
             }
            
    public ArrayList<AnswersDTO> getAnswersByQuestionID(int qID) {
            try{
            return answersDAO.getAnswersByQuestionID(qID);
            } catch (SQLException e){
            return  null;
            }
}
}

