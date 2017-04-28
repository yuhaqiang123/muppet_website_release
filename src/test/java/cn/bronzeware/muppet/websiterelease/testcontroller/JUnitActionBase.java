package cn.bronzeware.muppet.websiterelease.testcontroller;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.junit.BeforeClass;  
import org.springframework.mock.web.MockServletContext;  
import org.springframework.web.context.WebApplicationContext;  
import org.springframework.web.context.support.XmlWebApplicationContext;  
import org.springframework.web.servlet.HandlerAdapter;  
import org.springframework.web.servlet.HandlerExecutionChain;  
import org.springframework.web.servlet.HandlerMapping;  
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;  
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;  
/**  
* ˵���� JUnit����actionʱʹ�õĻ��� 
*  
* @author  ���� 
* @version ����ʱ�䣺2011-2-2 ����10:27:03   
*/   
public class JUnitActionBase {  
   private static HandlerMapping handlerMapping;  
   private static HandlerAdapter handlerAdapter;  
   /** 
    * ��ȡspring3 MVC�����ļ� 
    */  
   ///@BeforeClass  
public static void setUp() {  
       if (handlerMapping == null) {  
           String[] configs = { "classpath:applicationContext-mvc.xml" };  
           XmlWebApplicationContext context = new XmlWebApplicationContext();  
           context.setConfigLocations(configs);
           MockServletContext msc = new MockServletContext();  
           context.setServletContext(msc);     
          
           context.refresh();
           msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);  
           
           handlerMapping = (HandlerMapping) context  
                   .getBean(DefaultAnnotationHandlerMapping.class);
           handlerAdapter = (HandlerAdapter) context.getBean(context.getBeanNamesForType(AnnotationMethodHandlerAdapter.class)[0]);     
       }
   }  
 
   /** 
    * ִ��request���������action 
    *  
    * @param request 
    * @param response 
    * @return 
    * @throws Exception 
    */  
   public ModelAndView excuteAction(HttpServletRequest request, HttpServletResponse response)  
throws Exception {  
       HandlerExecutionChain chain = handlerMapping.getHandler(request);  
       final ModelAndView model = handlerAdapter.handle(request, response,  
               chain.getHandler()); 
       return model;  
   }  
}  