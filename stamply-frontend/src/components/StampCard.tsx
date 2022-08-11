import React from 'react';
import styled from 'styled-components';
import Box from '@mui/material/Box';
import {Typography} from "@mui/material";

const Inner = styled(Box)(({theme}) => ({
    width: "405px",
    height: "644px",
    background: theme.palette.primary.main,
    borderRadius: "2rem", //TODO Add box shadow using mui
    display: "flex",
    flexFlow: "column nowrap",
}));

const StampsContainer = styled.div`
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  height: fit-content;
  margin: auto;
  background-color: red;
`

const Stamp = styled.div(({theme}) => ({
    width: "75px",
    height: "75px",
    margin: ".8rem 2rem",
    borderRadius: "50%",
    background: theme.palette.primary.light,
}))


const StyledTypography = styled(Typography)(({theme}) => ({
    color: "white",
    margin: "2rem auto"
}))

export default function StampCard() {
    const stamps = Array.from(Array(10).keys()).map(x => <Stamp key={x}/>)

    return (
        <Inner>
            <StyledTypography variant="h4">Darmowa Kawa</StyledTypography>
            <StampsContainer>
                {stamps}
            </StampsContainer>
        </Inner>
    )
}
