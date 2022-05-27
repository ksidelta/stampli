import React from 'react';
import styled from 'styled-components';

import Button from '@mui/material/Button';
import { createTheme, ThemeProvider } from '@mui/material';


export default function ButtonComponent(params: { text: string, link: string, button: number, onClick?: () => void }) {
  const color = ['fb', 'google', 'apple'][params.button] as 'fb' | 'google' | 'apple';
  const backgroundColors = {
    'fb': '#000000',
    'google': '#DB4437',
    'apple': '#4267B2'
  }
  const theme = createTheme({
    palette: {
      primary: {
        main: backgroundColors[color],
      },
      secondary: {
        main: '#f44336',
      },
    },
  });

  const Inner = styled.div`
  width: calc(100% - .8rem);
  margin: .4rem;
  max-width: 20rem;
  `;

  return (
    <Inner>
      <ThemeProvider theme={theme}>
        <Button variant='contained' onClick={() => params.onClick && params.onClick()} color={"primary"} size='large' sx={{ width: '100%' }}
          href={params.link}>
          {params.text}
        </Button>
      </ThemeProvider>
    </Inner>
  );
}