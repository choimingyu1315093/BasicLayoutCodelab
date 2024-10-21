![스크린샷 2024-10-21 오전 11 14 08](https://github.com/user-attachments/assets/b3846ba9-cbd1-4593-b488-c76caab01af1)

바텀네비게이션, Scaffold, LazyRow, LazyHorizontalGrid, Column, rememberScrollState, Spacer, TextField, 

@Composable
fun HomeSection(
    @StringRes title: Int,
    content: @Composable ()->Unit,
    modifier: Modifier = Modifier)
{
    Column(modifier){
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}
