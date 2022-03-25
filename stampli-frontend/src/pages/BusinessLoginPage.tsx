import React from 'react';
import { Header } from '../components/simple/Header';
import { MobilePage } from './base/MobilePage';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { Content } from '../components/simple/container/layout/structure/Content';
import { BusinessLogin } from '../v2/components/complex/login/BusinessLogin';
import { InjectionContext } from '../components/stated/context/InjectionContext';

export const BusinessLoginPage = () => (
  <MobilePage>
    <Header title={'Zaloguj się do konsoli biznesu'} icon={faArrowLeft} />
    <Content>
      <InjectionContext.Consumer>
        {({ config: { openIdConfiguration }, tokenService, loginService, registerService, externalTokenService }) => (
          <BusinessLogin
            loginService={loginService}
            openIdConfiguration={openIdConfiguration}
            onSuccess={(token) => {
              externalTokenService.regenerateToken(token);
            }}
            registerService={registerService}
          />
        )}
      </InjectionContext.Consumer>
    </Content>
  </MobilePage>
);
