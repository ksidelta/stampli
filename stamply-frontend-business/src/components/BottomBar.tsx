import React from 'react';
import styled from 'styled-components';
import {AppBar, Fab, Box, IconButton, Toolbar, Typography} from "@mui/material";
import menu from "../img/restaurant_menu_black_24dp.svg";
import wifi from "../img/wifi_black_24dp.svg";
import qr from "../img/qr_code_black_24dp.svg";
import discount from "../img/discount_black_24dp.svg";
import options from "../img/menu_black_24dp.svg";
import {Link} from "react-router-dom";

const StyledA = styled.a`
  color: black;
  
  &&:link {
    text-decoration: none;
  }

  &&:visited {
    text-decoration: none;
  }

  &&:hover {
    text-decoration: none;
  }

  &&:active {
    text-decoration: none;
  }
`

const StyledLink = styled(Link)`
  color: black;
  
  &&:link {
    text-decoration: none;
  }

  &&:visited {
    text-decoration: none;
  }

  &&:hover {
    text-decoration: none;
  }

  &&:active {
    text-decoration: none;
  }
`

const StyledFab = styled(Fab)({
    position: 'absolute',
    zIndex: 1,
    top: -30,
    left: 0,
    right: 0,
    margin: '0 auto',
});

const StyledIconButton = styled(IconButton)({
    display: "flex",
    flexFlow: "column nowrap",
})

export default function BottomBar() {
    return (
        <AppBar position="sticky" color="primary"
                sx={{backgroundColor: 'primary.light', color: '#000', top: 'auto', bottom: 0}}>
            <Toolbar>
                <Box sx={{flexGrow: 1}}/>
                <StyledLink to="/menu">
                    <StyledIconButton color="inherit" aria-label="restaurant menu">
                        <img src={menu} alt={"Restaurant menu icon"}/>
                        <Typography variant="button">menu</Typography>
                    </StyledIconButton>
                </StyledLink>
                <StyledA href="https://content-media.pl/" target="_blank" referrerPolicy="origin">
                    <StyledIconButton color="inherit" aria-label="wifi">
                        <img src={wifi} alt={"WiFi icon"}/>
                        <Typography variant="button">WiFi marketing</Typography>
                    </StyledIconButton>
                </StyledA>
                <Box sx={{flexGrow: 1}}/>
                <StyledLink to="/qr">
                    <StyledFab color="primary" aria-label="scan qr code">
                        <img src={qr} alt={"QR Code icon"}/>
                    </StyledFab>
                </StyledLink>
                <Box sx={{flexGrow: 1}}/>
                <StyledLink to="/discounts">
                    <StyledIconButton color="inherit" aria-label="discounts">
                        <img src={discount} alt={"Discount icon"}/>
                        <Typography variant="button">discount</Typography>
                    </StyledIconButton>
                </StyledLink>
                <StyledLink to="/options">
                    <StyledIconButton color="inherit" aria-label="options">
                        <img src={options} alt={"Options icon"}/>
                        <Typography variant="button">options</Typography>
                    </StyledIconButton>
                </StyledLink>
                <Box sx={{flexGrow: 1}}/>
            </Toolbar>
        </AppBar>
    )
}