import org.mesis.bila.AccountCee;
import org.mesis.bila.NodeType;
import grails.util.Environment


class BootStrap {

	def bilaService

    def init = { servletContext ->
	
		if (!AccountCee.count()) {
			bilaService.loadAvis()
			bilaService.loadBilaCee()
			bilaService.loadPC()
			bilaService.loadDocType()
			
			if (Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) {
				bilaService.loadMockDoc(10)
			}
		}
	}
	
    def destroy = {
    }
	

}
