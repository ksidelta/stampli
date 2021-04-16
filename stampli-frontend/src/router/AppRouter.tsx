import React, { useEffect, useState } from 'react';
import { Redirect, Route, Router, Switch } from 'react-router-dom';
import { EntryPage } from '../pages/EntryPage';
import { BusinessLoginPage } from '../pages/BusinessLoginPage';
import { RoutingServiceInstance } from './services/RoutingService';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { BasicServicesFactory } from '../services/factory/BasicServicesFactory';
import { UnauthenticatedCondition } from '../components/stated/condition/authentication/UnauthenticatedCondition';
import { AuthenticatedCondition } from '../components/stated/condition/authentication/AuthenticatedCondition';
import { BusinessOptionsPage } from '../pages/BusinessOptionsPage';
import { ServicesBundle } from '../services/ServicesBundle';

export const RouterServiceContext = React.createContext(RoutingServiceInstance);

export const AppRouter = () => {
  const [servicesBundle, updateServicesBundle] = useState<ServicesBundle | undefined>(undefined);

  useEffect(() => {
    new BasicServicesFactory().create().then(bundle => updateServicesBundle(bundle));
  }, []);

  if (servicesBundle === undefined) {
    return <div>Loading</div>;
  }

  return (
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
            <Route path={'/business/'}>
              <UnauthenticatedCondition>
                <BusinessLoginPage />
              </UnauthenticatedCondition>
              <AuthenticatedCondition>
                <BusinessOptionsPage />
              </AuthenticatedCondition>
            </Route>
          </Switch>
        </Router>
      </RouterServiceContext.Provider>
    </InjectionContext.Provider>
  );
};
