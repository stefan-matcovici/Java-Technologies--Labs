package ro.uaic.info.technologies.documentmanager.interceptors;

import ro.uaic.info.technologies.documentmanager.services.AuthService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@LoggedIn
@Interceptor
public class LoggedInInterceptor implements Serializable {

    @Inject
    private AuthService authService;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        if (authService.getCurrentUser() == null) {
            throw new Exception("Not logged in you fool");
        }

        return invocationContext.proceed();
    }

}
