import React, { ReactNode } from 'react';
import styled from 'styled-components';
import { CommonPage } from './CommonPage';

const Inner = styled.div`
  width: 768px;
  margin-left: auto;
  margin-right: auto;

  @media screen and (max-width: 768px) {
    width: 100%;
    height: 100%;
    margin-left: 0;
    margin-right: 0;
  }

  min-height: 100vh;
  background-color: var(--bg-color);
`;

export const MobilePage: React.FunctionComponent = ({ children }: { children?: ReactNode }) => (
  <CommonPage className={'krincz'}>
    <Inner>{children}</Inner>
  </CommonPage>
);
