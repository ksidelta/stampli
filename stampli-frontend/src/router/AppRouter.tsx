import React from 'react';
import { Redirect, Route, Router, Switch } from 'react-router-dom';
import { EntryPage } from '../pages/EntryPage';
import { BusinessLoginPage } from '../pages/BusinessLoginPage';
import { RoutingServiceInstance } from './services/RoutingService';

export const RouterServiceContext = React.createContext(RoutingServiceInstance);

export const AppRouter = () => (
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
          <BusinessLoginPage />
        </Route>
      </Switch>
    </Router>
  </RouterServiceContext.Provider>
);
