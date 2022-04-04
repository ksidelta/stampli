import React from 'react';
import styled from 'styled-components';
import TextField from '@mui/material/TextField';

const Inner = styled.div`
  width: calc(100% - .8rem);
  margin: .4rem;
  max-width: 20rem;
`

export default function Component (params: {text: string, required: boolean}) {
    if (params.required) {
        return (
            <Inner>
                <TextField label={params.text} sx={{width: "100%"}} variant="outlined" required/>
            </Inner>
        )
    } else {
        return (
            <Inner>
                <TextField label={params.text} sx={{width: "100%"}} variant="outlined"/>
            </Inner>
        )
    }
}