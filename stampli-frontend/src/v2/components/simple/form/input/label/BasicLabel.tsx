import React from 'react';
import styled from 'styled-components';

export const BasicLabel = ({ children }: { children: string }) => <Inner>{children}</Inner>;

const Inner = styled.label`
  display: block;
  text-align: left;

  font-size: calc(var(--gap));
  padding-bottom: calc(0.2 * var(--gap));
`;
