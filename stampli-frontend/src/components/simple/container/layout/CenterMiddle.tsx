import React, { ReactNode } from 'react';
import { Center } from './align/Center';
import { Middle } from './align/Middle';

export const CenterMiddle = ({ children }: { children: ReactNode }) => (
  <Middle>
    <Center>{children}</Center>
  </Middle>
);
