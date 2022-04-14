import React from 'react';
import styled from 'styled-components';

import Button from '@mui/material/Button';

const Inner = styled.div`
  width: calc(100% - .8rem);
  margin: .4rem;
  max-width: 20rem;
`;

export default function ButtonComponent(params: { text: string, link: string, button: string, onClick?: () => void }) {
  const color = ['fb', 'google', 'apple'].includes(params.button) ? params.button : undefined;
  return (
    <Inner>
      <Button variant='contained' onClick={() => params.onClick && params.onClick()} color={color} size='large' sx={{ width: '100%' }}
              href={params.link}>
        {params.text}
      </Button>
    </Inner>
  );
}