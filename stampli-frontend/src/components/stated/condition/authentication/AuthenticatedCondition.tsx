import * as React from 'react';
import { Condition } from '../../../simple/condition/Condition';
import { InjectionContext } from '../../context/InjectionContext';
import { Observer, observer } from 'mobx-react';

export const AuthenticatedCondition = observer(({ children }: { children: React.ReactNode }) => {
  return (
    <InjectionContext.Consumer>
      {({ tokenService }) => (
        <Observer>{() => <Condition predicate={tokenService.isAuthenticated()}>{children}</Condition>}</Observer>
      )}
    </InjectionContext.Consumer>
  );
});
