import React from 'react';
import styled, { css } from 'styled-components';

export const StateStyledTextInput = () => <div></div>;

const StyledTextInput = styled.div<LoadableDiv>`
  ${props =>
    props.isLoading &&
    css`
      & input {
        border-color: orange;
      }
    `}

  ${props =>
    props.isSuccess &&
    css`
      & input {
        border-color: orange;
      }
    `}
`;

interface LoadableDiv {
  isLoading: boolean;
  isSuccess: boolean;
}
