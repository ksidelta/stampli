import React from 'react';
import styled from 'styled-components';

import Button from '@mui/material/Button';

const Inner = styled.div`
  width: calc(100% - .8rem);
  margin: .4rem;
  max-width: 20rem;
`

export default function ButtonComponent (params: {text: string, link: string, button: string}) {
    /*oneacik mi za to yebnie*/
    switch (params.button) {
        case "apple":
            return (
                <Inner>
                <Button variant="contained" size="large" color="apple" sx={{width: "100%"}} href={params.link}>
                    {params.text}
                </Button>
                </Inner>
            )
        case "google":
            return (
                <Inner>
                <Button variant="contained" size="large" color="google" sx={{width: "100%"}} href={params.link}>
                    {params.text}
                </Button>
                </Inner>
            )
        case "fb":
            return (
            <Inner>
                <Button variant="contained" size="large" color="fb" sx={{width: "100%"}} href={params.link}>
                    {params.text}
                </Button>
            </Inner>
            )
    }
    return (
        <Inner>
        <Button variant="contained" size="large" sx={{width: "100%"}} href={params.link}>
            {params.text}
        </Button>
        </Inner>
    )
}