package ro.uaic.info.technologies.documentmanager.interceptors;

import ro.uaic.info.technologies.documentmanager.models.User;
import ro.uaic.info.technologies.documentmanager.services.AuthService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@AdminLoggedIn
@Interceptor
public class AdminLoggedInInterceptor implements Serializable {

    @Inject
    private AuthService authService;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        if (authService.getCurrentUser().getType() != User.UserType.admin) {
            throw new Exception("Not logged in as admin you fool");
        }

        return invocationContext.proceed();
    }
}
