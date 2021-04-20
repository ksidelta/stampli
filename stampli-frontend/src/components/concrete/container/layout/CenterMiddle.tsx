import React, { ReactNode } from 'react';
import { Center } from '../../../simple/container/layout/align/Center';
import { Middle } from '../../../simple/container/layout/align/Middle';

export const CenterMiddle = ({ children }: { children: ReactNode }) => (
  <Middle>
    <Center>{children}</Center>
  </Middle>
);
