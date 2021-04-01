import GoogleLogin from 'react-google-login';
import React from 'react';
import { Configuration } from '../../../../services/config/Configuration';
import styled from 'styled-components';

export const GoogleLoginButton = ({
  config,
  onSuccess
}: {
  config: Configuration;
  onSuccess: (token: string) => void;
}) => (
  <StyledGoogleLogin
    clientId={config.openIdConfiguration.googleClientId}
    buttonText="Login"
    onSuccess={x => console.log(x)}
    onFailure={x => console.error(x)}
    cookiePolicy={'single_host_origin'}
  />
);

const StyledGoogleLogin = styled(GoogleLogin)`
  height: calc(3 * var(--gap));
`;
