import GoogleLogin, { GoogleLoginResponse } from 'react-google-login';
import React from 'react';
import styled from 'styled-components';
import { OpenIdConfiguration } from '../../../../services/config/parts/OpenIdConfiguration';

export const GoogleLoginButton = ({
  config,
  onSuccess,
  onFailure = console.error
}: {
  config: OpenIdConfiguration;
  onSuccess: (token: string) => void;
  onFailure?: (error: string) => void;
}) => (
  <StyledGoogleLogin
    clientId={config.googleClientId}
    buttonText="Login"
    onSuccess={x => ('accessToken' in x ? onSuccess((x as GoogleLoginResponse).accessToken) : onFailure('Offline!'))}
    onFailure={x => onFailure(x)}
    cookiePolicy={'single_host_origin'}
  />
);

const StyledGoogleLogin = styled(GoogleLogin)`
  height: calc(5 * var(--gap));

  & span {
    font-size: calc(1.2 * var(--gap));
  }
`;
