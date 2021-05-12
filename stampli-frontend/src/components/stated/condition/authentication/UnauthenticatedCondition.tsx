import * as React from 'react';
import { Condition } from '../../../simple/condition/Condition';
import { InjectionContext } from '../../context/InjectionContext';
import { Observer, observer } from 'mobx-react';

export const UnauthenticatedCondition = observer(({ children }: { children: React.ReactNode }) => (
  <InjectionContext.Consumer>
    {({ tokenService }) => (
      <Observer>{() => <Condition predicate={!tokenService.isAuthenticated()}>{children}</Condition>}</Observer>
    )}
  </InjectionContext.Consumer>
));
