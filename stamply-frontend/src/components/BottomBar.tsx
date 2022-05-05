import React, {useState} from 'react';
import styled from 'styled-components';
import {AppBar, Fab, IconButton, Toolbar, Typography} from "@mui/material";
import "../img/restaurant_menu_black_24dp.svg"
import "../img/approval_black_24dp.svg"
import "../img/qr_code_black_24dp.svg"
import "../img/discount_black_24dp.svg"
import "../img/menu_black_24dp.svg"
import Box from "@mui/material/Box";
import {InjectionComponent} from "./stated/context/InjectionComponent";
import {RouterServiceContext} from "../router/AppRouter";


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

// const StyledAppBar = styled(AppBar)(({ theme }) => ({
//     "--cradleMargin": "8px", // 1
//     "--fabRadius": "28px", // 2
//     "--cradleRadius": "calc(var(--fabRadius) + var(--cradleMargin))",
//     background: `
//     radial-gradient(
//       circle at 50% 0,
//       rgba(0, 0, 0, 0) var(--cradleRadius),
//       ${theme.palette.primary.main} var(--cradleRadius),
//       ${theme.palette.primary.main} 100%
//     )
//   `,
//     boxShadow: "none", // 3
//     filter: `drop-shadow(0 -1px 1px rgba(0, 0, 0, .2))` // 3
// }));


export default function BottomBar() {
    return (
        <RouterServiceContext.Consumer>{(historyService) =>
            <AppBar position="sticky" color="primary"
                    sx={{backgroundColor: 'primary.light', color: '#000', top: 'auto', bottom: 0}}>
                <Toolbar> {/* We can also use "BottomNavigation element */}
                    <Box sx={{flexGrow: 1}}/>
                    <StyledIconButton color="inherit" aria-label="restaurant menu"
                                      onClick={historyService.goToMenu.bind(historyService)}>
                        <img src={"/assets/img/restaurant_menu_black_24dp.svg"} alt={"Restaurant menu icon"}/>
                        <Typography variant="button">menu</Typography>
                    </StyledIconButton>
                    <StyledIconButton color="inherit" aria-label="stamp"
                                      onClick={historyService.goToStamps.bind(historyService)}>
                        <img src={"/assets/img/approval_black_24dp.svg"} alt={"Stamp icon"}/>
                        <Typography variant="button">stamples</Typography>
                    </StyledIconButton>
                    <StyledFab color="primary" aria-label="scan qr code" onClick={historyService.goToScanner.bind(historyService)}>
                        <img src={"/assets/img/qr_code_black_24dp.svg"} alt={"QR Code icon"}/>
                    </StyledFab>
                    <Box sx={{flexGrow: 2}}/>
                    <StyledIconButton color="inherit" aria-label="discounts" onClick={historyService.goToDiscounts.bind(historyService)}>
                        <img src={"/assets/img/discount_black_24dp.svg"} alt={"Discount icon"}/>
                        <Typography variant="button">discount</Typography>
                    </StyledIconButton>
                    <StyledIconButton color="inherit" aria-label="options" onClick={historyService.goToOptions.bind(historyService)}>
                        <img src={"/assets/img/menu_black_24dp.svg"} alt={"Options icon"}/>
                        <Typography variant="button">options</Typography>
                    </StyledIconButton>
                    <Box sx={{flexGrow: 1}}/>
                </Toolbar>
            </AppBar>
        }
        </RouterServiceContext.Consumer>
    )
}