import React, { ReactNode } from 'react';
import { InjectionContext } from './InjectionContext';
import { ServicesBundle } from '../../../services/ServicesBundle';

export const InjectionComponent = ({ bundle, children }: { bundle: ServicesBundle; children?: ReactNode }) => (
  <>
    <InjectionContext.Provider value={bundle}>{children}</InjectionContext.Provider>
  </>
);
