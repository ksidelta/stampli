import React from 'react';
import { Redirect, Route, Router, Switch } from 'react-router-dom';
import { EntryPage } from '../pages/EntryPage';
import { BusinessLoginPage } from '../pages/BusinessLoginPage';
import { RoutingServiceInstance } from './services/RoutingService';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { BasicServicesFactory } from '../services/factory/BasicServicesFactory';
import { UnauthenticatedCondition } from '../components/stated/condition/authentication/UnauthenticatedCondition';
import { AuthenticatedCondition } from '../components/stated/condition/authentication/AuthenticatedCondition';
import { TestPage } from '../pages/TestPage';

export const RouterServiceContext = React.createContext(RoutingServiceInstance);

const servicesBundle = new BasicServicesFactory().create();

export const AppRouter = () => (
  <InjectionContext.Provider value={servicesBundle}>
    <RouterServiceContext.Provider value={RoutingServiceInstance}>
      <Router history={RoutingServiceInstance.history}>
        <Switch>
          <Route path={'/'} exact={true}>
            <Redirect to={'/home'} />
          </Route>
          <Route path={'/home'} exact={true}>
            <EntryPage />
          </Route>
          <Route path={'/business/login'}>
            <UnauthenticatedCondition>
              <BusinessLoginPage />
            </UnauthenticatedCondition>
            <AuthenticatedCondition>
              <TestPage />
            </AuthenticatedCondition>
          </Route>
        </Switch>
      </Router>
    </RouterServiceContext.Provider>
  </InjectionContext.Provider>
);
