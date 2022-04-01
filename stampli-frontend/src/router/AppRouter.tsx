import React, { useEffect, useState } from 'react';
import { Redirect, Route, Router, Switch } from 'react-router-dom';
import { ClientLoginPage } from '../pages/ClientLoginPage';
import { BusinessLoginPage } from '../pages/BusinessLoginPage';
import { RoutingServiceInstance } from './services/RoutingService';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { BasicServicesFactory } from '../services/factory/BasicServicesFactory';
import { UnauthenticatedCondition } from '../components/stated/condition/authentication/UnauthenticatedCondition';
import { AuthenticatedCondition } from '../components/stated/condition/authentication/AuthenticatedCondition';
import { BusinessOptionsPage } from '../pages/BusinessOptionsPage';
import { ServicesBundle } from '../services/ServicesBundle';
import { Routes } from './routes/Routes';
import { BusinessSettingsPage } from '../pages/BusinessSettingsPage';
import { QRBusinessDisplayPage } from '../pages/QRBusinessDisplayPage';
import { QRClaimPage } from '../pages/QRClaimPage';
import { QRScanPage } from '../pages/QRScanPage';

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
              <ClientLoginPage />
            </Route>
            <Route path={'/scanner'} exact={true}>
              <QRScanPage />
            </Route>
            <Route path={Routes.challenge.claim(undefined, ':businessId', ':issuerId', ':nonce')}>
              <UnauthenticatedCondition>
                <BusinessLoginPage />
              </UnauthenticatedCondition>
              <AuthenticatedCondition>
                <QRClaimPage />
              </AuthenticatedCondition>
            </Route>
            <Route path={Routes.business.root}>
              <UnauthenticatedCondition>
                <BusinessLoginPage />
              </UnauthenticatedCondition>

              <AuthenticatedCondition>
                <Route path={Routes.business.root} exact={true}>
                  <BusinessOptionsPage />
                </Route>
                <Route path={Routes.business.settings} exact={true}>
                  <BusinessSettingsPage />
                </Route>
                <Route path={Routes.business.qrCode} exact={true}>
                  <QRBusinessDisplayPage />
                </Route>
              </AuthenticatedCondition>
            </Route>
          </Switch>
        </Router>
      </RouterServiceContext.Provider>
    </InjectionContext.Provider>
  );
};
