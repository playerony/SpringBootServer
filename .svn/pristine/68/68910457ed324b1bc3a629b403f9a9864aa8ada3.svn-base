package pl.kamsoft.nfz.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;

import pl.kamsoft.nfz.XMLGeneratror.xml.exception.XMLException;

@ControllerAdvice
public class GlobalException {
	//private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);
	
	@ExceptionHandler(DatabaseException.class)
	public String handleDatabaseException(DatabaseException e, Model model) {
		model.addAttribute("message", e.getMessage());
		//logger.info("Database exception");
		
		return "error/errorPage";
	}
	
	@ExceptionHandler(InputException.class)
	public String handleInputException (InputException e, Model model) {
		model.addAttribute("message", e.getMessage());
		//logger.info("Input exception");
		
		return "error/errorPage";
	}
	
	@ExceptionHandler(XMLException.class)
	public String handleXMLException (XMLException e, Model model) {
		model.addAttribute("message", e.getMessage());
		e.printStackTrace();
		
		return "error/errorPage";
	}
}
