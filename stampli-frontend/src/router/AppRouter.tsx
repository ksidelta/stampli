import React, { useEffect, useState } from 'react';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Redirect, Route, Router, Switch } from 'react-router-dom';
import { ClientLoginPage } from '../pages/ClientLoginPage';
import { RoutingServiceInstance } from './services/RoutingService';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { BasicServicesFactory } from '../services/factory/BasicServicesFactory';
import { UnauthenticatedCondition } from '../components/stated/condition/authentication/UnauthenticatedCondition';
import { AuthenticatedCondition } from '../components/stated/condition/authentication/AuthenticatedCondition';
import { ServicesBundle } from '../services/ServicesBundle';
import { Routes } from './routes/Routes';
import LoginEmailPage from "../pages/ClientLoginEmailPage";
import RegisterEmailPage from "../pages/ClientRegisterEmailPage";

declare module '@mui/material/styles' {
  interface Palette {
    apple?: Palette['primary'];
    google?: Palette['primary'];
    fb?: Palette['primary'];
  }
  interface PaletteOptions {
    apple: PaletteOptions['primary'];
    google: PaletteOptions['primary'];
    fb: PaletteOptions['primary'];
  }
}


const theme = createTheme({
  palette: {
    primary: {
      // light: '#fde8e7',
      main: '#ff3a17',
      // dark: '#cb1500',
    },
    secondary: {
      // light: '#d9fffe',
      main: '#00ffff',
      // dark: '#008c82',
    },
    apple: {
      contrastText: '#f5f5f5',
      main: '#000000',
    },
    google: {
      contrastText: '#f5e2e1',
      main: '#DB4437'
    },
    fb: {
      contrastText: '#e1e8f5',
      main: '#4267B2'
    }
  },
});


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
      <ThemeProvider theme={theme}>
        <InjectionContext.Provider value={servicesBundle}>
      <RouterServiceContext.Provider value={RoutingServiceInstance}>
        <Router history={RoutingServiceInstance.history}>
          <Switch>
            <Route path={'/'} exact={true}>
              <Redirect to={'/login'} />
            </Route>
            <Route path={'/login'} exact={true}>
              <ClientLoginPage />
            </Route>
            <Route path={'/login/email'} exact={true}>
              <LoginEmailPage/>
            </Route>
            <Route path={'/register'} exact={true}>
              <Redirect to={'/register/email'} />
            </Route>
            <Route path={'/register/email'} exact={true}>
              <RegisterEmailPage/>
            </Route>
            <Route path={'/scanner'} exact={true}>
            </Route>
            <Route path={Routes.challenge.claim(undefined, ':businessId', ':issuerId', ':nonce')}>
              <UnauthenticatedCondition>
              </UnauthenticatedCondition>
              <AuthenticatedCondition>
              </AuthenticatedCondition>
            </Route>
            <Route path={Routes.business.root}>
              <UnauthenticatedCondition>
              </UnauthenticatedCondition>
                  <AuthenticatedCondition>
                    <Route path={Routes.business.root} exact={true}>
                    </Route>
                    <Route path={Routes.business.settings} exact={true}>
                    </Route>
                    <Route path={Routes.business.qrCode} exact={true}>
                    </Route>
                  </AuthenticatedCondition>
                </Route>
              </Switch>
            </Router>
          </RouterServiceContext.Provider>
        </InjectionContext.Provider>
      </ThemeProvider>
  );
};
