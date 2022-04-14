import React from 'react';
import { ServicesBundle } from '../../../services/ServicesBundle';

export const InjectionContext = React.createContext<ServicesBundle>((undefined as unknown) as ServicesBundle);
