import * as React from 'react';
import { Condition } from '../../../concrete/condition/Condition';
import { InjectionContext } from '../../context/InjectionContext';
import { observer } from 'mobx-react';

export const AuthenticatedCondition = observer(({ children }: { children: React.ReactNode }) => (
  <InjectionContext.Consumer>
    {({ tokenService }) => <Condition predicate={() => tokenService.isAuthenticated()}>{children}</Condition>}
  </InjectionContext.Consumer>
));
