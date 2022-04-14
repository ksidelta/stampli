import React from 'react';
import styled from 'styled-components';
import TextField from '@mui/material/TextField';

const Inner = styled.div`
  width: calc(100% - .8rem);
  margin: .4rem;
  max-width: 20rem;
`;

export default function Component({text, value, setValue, required}: { text: string, value: string, setValue: (text: string) => void, required: boolean }) {
  return (
    <Inner>
      <TextField
        label={text}
        sx={{ width: '100%' }}
        variant='outlined'
        required={required}
        value={value}
        onChange={(e) => setValue(e.target.value)}
      />
    </Inner>
  );
}