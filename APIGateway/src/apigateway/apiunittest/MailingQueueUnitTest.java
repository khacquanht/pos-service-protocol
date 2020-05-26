package apigateway.apiunittest;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;
public class MailingQueueUnitTest 
{
//http://localhost:83/api/mailing_queue?cm=list&dt={id:0}
//http://localhost:83/api/mailing_queue?cm=list&dt={id:1}
//http://localhost:83/api/mailing_queue?cm=list&dt={id:1}
public void addMailingQueue(){
	com.wahsis.utility_service.MailingQueue mailingqueue = new com.wahsis.utility_service.MailingQueue();
	StringBuilder vrfContent = new StringBuilder();
	mailingqueue.setId(1);
	mailingqueue.setEmailFrom("email_from_ver01");
	mailingqueue.setEmailTo("email_to_ver01");
	mailingqueue.setCc("cc_ver01");
	mailingqueue.setBcc("bcc_ver01");
	mailingqueue.setSubject("subject_ver01");
	mailingqueue.setMessage("message_ver01");
	mailingqueue.setAttach("attach_ver01");
	mailingqueue.setDate("2016-03-16");
	mailingqueue.setImportance(1);
	mailingqueue.setPriority(1);
	mailingqueue.setStatus(1);
	mailingqueue.setError("error_ver01");
	String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.MAILING_QUEUE, mailingqueue);
	com.wahsis.utility_service.MailingQueueHandler.getInstance().addMailingQueue(dt, vrfContent);
}
//public void updateMailingQueue(){
//	com.wahsis.utility_service.MailingQueue mailingqueue = new com.wahsis.utility_service.MailingQueue();
//	StringBuilder vrfContent = new StringBuilder();
//	mailingqueue.setId(1);
//	mailingqueue.setEmailFrom("email_from_ver01");
//	mailingqueue.setEmailTo("email_to_ver01");
//	mailingqueue.setCc("cc_ver01");
//	mailingqueue.setBcc("bcc_ver01");
//	mailingqueue.setSubject("subject_ver01");
//	mailingqueue.setMessage("message_ver01");
//	mailingqueue.setAttach("attach_ver01");
//	mailingqueue.setDate("2016-03-16");
//	mailingqueue.setImportance(1);
//	mailingqueue.setPriority(1);
//	mailingqueue.setStatus(1);
//	mailingqueue.setError("error_ver01");
//	String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.MAILING_QUEUE, mailingqueue);
//	com.wahsis.utility_service.MailingQueueHandler.getInstance().updateMailingQueue(dt, vrfContent);
//}
}
