import React from "react";
import styled from "styled-components";

import Button from '@mui/material/Button';

const Inner = styled.div`
  width: calc(100% - .8rem);
  margin: .2rem;
  max-width: 20rem;
`

export default function ButtonText (params: {text: string, link: string}) {
    return (
        <Inner>
            <Button href={params.link} sx={{width: "100%"}}>
                {params.text}
            </Button>
        </Inner>
    )
}