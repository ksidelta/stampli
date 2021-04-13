import * as React from 'react';
import { Condition } from '../../../concrete/condition/Condition';
import { InjectionContext } from '../../context/InjectionContext';
import { Observer, observer } from 'mobx-react';

export const UnauthenticatedCondition = observer(({ children }: { children: React.ReactNode }) => (
  <InjectionContext.Consumer>
    {({ tokenService }) => (
      <Observer>
        {() => {
          console.log(`KAWASICA: ${tokenService.isAuthenticated()}`);
          return <Condition predicate={tokenService.isAuthenticated()}>{children}</Condition>;
        }}
      </Observer>
    )}
  </InjectionContext.Consumer>
));
