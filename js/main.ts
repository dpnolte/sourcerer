import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
// types
/* generated @ 2018-11-13T11:36:59.307 */
export namespace MainTypes {
  export interface AbsListViewAttributes extends AdapterViewAttributes {
    cacheColorHint?: string;
    choiceMode?: ChoiceModeEnum;
    drawSelectorOnTop?: boolean;
    fastScrollAlwaysVisible?: boolean;
    fastScrollEnabled?: boolean;
    fastScrollStyle?: string;
    listSelector?: string;
    scrollingCache?: boolean;
    smoothScrollbar?: boolean;
    stackFromBottom?: boolean;
    textFilterEnabled?: boolean;
    transcriptMode?: TranscriptModeEnum;
  }
  export interface AbsListViewLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
  }
  export interface AbsSeekBarAttributes extends ProgressBarAttributes {
    SeekBar_splitTrack?: boolean;
    SeekBar_thumb?: number;
    SeekBar_thumbOffset?: string;
    SeekBar_thumbTint?: number;
    SeekBar_thumbTintMode?: number;
    SeekBar_tickMark?: number;
    SeekBar_tickMarkTint?: number;
    SeekBar_tickMarkTintMode?: number;
    thumbTint?: string;
    thumbTintMode?: ThumbTintModeEnum;
    tickMarkTint?: string;
    tickMarkTintMode?: TickMarkTintModeEnum;
  }
  export interface AbsSpinnerAttributes extends AdapterViewAttributes {
  }
  export enum AccessibilityLiveRegionEnum {
    assertive = 'assertive',
    none = 'none',
    polite = 'polite'
  }
  export interface ActionMenuViewAttributes extends LinearLayoutAttributes {
  }
  export interface ActionMenuViewLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
  }
  export interface AdapterViewAnimatorAttributes extends AdapterViewAttributes {
    animateFirstView?: boolean;
    inAnimation?: number;
    outAnimation?: number;
  }
  export interface AdapterViewAttributes extends ViewGroupAttributes {
  }
  export interface AdapterViewFlipperAttributes extends AdapterViewAnimatorAttributes {
    autoStart?: boolean;
    flipInterval?: number;
  }
  export enum AlignmentModeEnum {
    alignBounds = 'alignBounds',
    alignMargins = 'alignMargins'
  }
  export interface AutoCompleteTextViewAttributes extends EditTextAttributes {
    completionHint?: string;
    completionThreshold?: number;
    dropDownAnchor?: string;
    dropDownHeight?: string | DropDownHeightEnum;
    dropDownHorizontalOffset?: number;
    dropDownVerticalOffset?: number;
    dropDownWidth?: string | DropDownWidthEnum;
  }
  export enum AutoLinkFlagsEnum {
    all = 'all',
    email = 'email',
    map = 'map',
    none = 'none',
    phone = 'phone',
    web = 'web'
  }
  export enum AutoSizeTextTypeEnum {
    none = 'none',
    uniform = 'uniform'
  }
  export enum BackgroundTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export enum BreakStrategyEnum {
    balanced = 'balanced',
    high_quality = 'high_quality',
    simple = 'simple'
  }
  export interface ButtonAttributes extends TextViewAttributes {
  }
  export enum ButtonTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface CalendarViewAttributes extends FrameLayoutAttributes {
    dateTextAppearance?: string;
    firstDayOfWeek?: number;
    focusedMonthDateColor?: string;
    maxDate?: number;
    minDate?: number;
    selectedDateVerticalBar?: string;
    selectedWeekBackgroundColor?: string;
    showWeekNumber?: boolean;
    shownWeekCount?: number;
    unfocusedMonthDateColor?: string;
    weekDayTextAppearance?: string;
    weekNumberColor?: string;
    weekSeparatorLineColor?: string;
  }
  export interface CheckBoxAttributes extends CompoundButtonAttributes {
  }
  export enum CheckMarkTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface CheckedTextViewAttributes extends TextViewAttributes {
    checkMark?: string;
    checkMarkTint?: string;
    checkMarkTintMode?: CheckMarkTintModeEnum;
    checked?: boolean;
  }
  export enum ChoiceModeEnum {
    multipleChoice = 'multipleChoice',
    multipleChoiceModal = 'multipleChoiceModal',
    none = 'none',
    singleChoice = 'singleChoice'
  }
  export interface ChronometerAttributes extends TextViewAttributes {
    countDown?: boolean;
    format?: string;
  }
  export interface CompoundButtonAttributes extends ButtonAttributes {
    button?: string;
    buttonTint?: string;
    buttonTintMode?: ButtonTintModeEnum;
    checked?: boolean;
  }
  export interface DatePickerAttributes extends FrameLayoutAttributes {
    firstDayOfWeek?: number;
  }
  export enum DrawableTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export enum DrawingCacheQualityEnum {
    auto = 'auto',
    high = 'high',
    low = 'low'
  }
  export enum DropDownHeightEnum {
    fill_parent = 'fill_parent',
    match_parent = 'match_parent',
    wrap_content = 'wrap_content'
  }
  export enum DropDownWidthEnum {
    fill_parent = 'fill_parent',
    match_parent = 'match_parent',
    wrap_content = 'wrap_content'
  }
  export interface EditTextAttributes extends TextViewAttributes {
  }
  export enum EllipsizeEnum {
    end = 'end',
    marquee = 'marquee',
    middle = 'middle',
    none = 'none',
    start = 'start'
  }
  export interface ExpandableListViewAttributes extends ListViewAttributes {
    childDivider?: string;
    childIndicator?: string;
    groupIndicator?: string;
  }
  export enum FocusableEnum {
    auto = 'auto'
  }
  export enum ForegroundGravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    top = 'top'
  }
  export enum ForegroundTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface FrameLayoutAttributes extends ViewGroupAttributes {
    measureAllChildren?: boolean;
  }
  export interface FrameLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: LayoutGravityFlagsEnum[];
  }
  export enum GravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum GravityFlagsEnum_ {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum GravityFlagsEnum__ {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum GravityFlagsEnum___ {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum GravityFlagsEnum____ {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export interface GridLayoutAttributes extends ViewGroupAttributes {
    alignmentMode?: AlignmentModeEnum;
    columnCount?: number;
    columnOrderPreserved?: boolean;
    orientation?: OrientationEnum_;
    rowCount?: number;
    rowOrderPreserved?: boolean;
    useDefaultMargins?: boolean;
  }
  export interface GridLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }
  export interface GridViewAttributes extends AbsListViewAttributes {
    columnWidth?: string;
    gravity?: number | GravityFlagsEnum____[];
    horizontalSpacing?: string;
    numColumns?: number | NumColumnsEnum;
    stretchMode?: StretchModeEnum;
    verticalSpacing?: string;
  }
  export interface HorizontalScrollViewAttributes extends FrameLayoutAttributes {
    fillViewport?: boolean;
  }
  export enum HyphenationFrequencyEnum {
    full = 'full',
    none = 'none',
    normal = 'normal'
  }
  export interface ImageButtonAttributes extends ImageViewAttributes {
  }
  export interface ImageSwitcherAttributes extends ViewSwitcherAttributes {
  }
  export interface ImageViewAttributes extends ViewAttributes {
    adjustViewBounds?: boolean;
    baseline?: string;
    baselineAlignBottom?: boolean;
    cropToPadding?: boolean;
    drawableAlpha?: number;
    maxHeight?: string;
    maxWidth?: string;
    scaleType?: ScaleTypeEnum;
    src?: string;
    tint?: string;
    tintMode?: number;
  }
  export enum ImeOptionsFlagsEnum {
    actionDone = 'actionDone',
    actionGo = 'actionGo',
    actionNext = 'actionNext',
    actionNone = 'actionNone',
    actionPrevious = 'actionPrevious',
    actionSearch = 'actionSearch',
    actionSend = 'actionSend',
    actionUnspecified = 'actionUnspecified',
    flagNavigateNext = 'flagNavigateNext',
    flagNavigatePrevious = 'flagNavigatePrevious',
    flagNoAccessoryAction = 'flagNoAccessoryAction',
    flagNoEnterAction = 'flagNoEnterAction',
    flagNoExtractUi = 'flagNoExtractUi',
    flagNoFullscreen = 'flagNoFullscreen',
    flagNoPersonalizedLearning = 'flagNoPersonalizedLearning',
    normal = 'normal'
  }
  export enum ImportantForAccessibilityEnum {
    auto = 'auto',
    no = 'no',
    noHideDescendants = 'noHideDescendants',
    yes = 'yes'
  }
  export enum ImportantForAutofillFlagsEnum {
    auto = 'auto',
    no = 'no',
    noExcludeDescendants = 'noExcludeDescendants',
    yes = 'yes',
    yesExcludeDescendants = 'yesExcludeDescendants'
  }
  export enum IndeterminateTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export enum InputTypeFlagsEnum {
    date = 'date',
    datetime = 'datetime',
    none = 'none',
    number = 'number',
    numberDecimal = 'numberDecimal',
    numberPassword = 'numberPassword',
    numberSigned = 'numberSigned',
    phone = 'phone',
    text = 'text',
    textAutoComplete = 'textAutoComplete',
    textAutoCorrect = 'textAutoCorrect',
    textCapCharacters = 'textCapCharacters',
    textCapSentences = 'textCapSentences',
    textCapWords = 'textCapWords',
    textEmailAddress = 'textEmailAddress',
    textEmailSubject = 'textEmailSubject',
    textFilter = 'textFilter',
    textImeMultiLine = 'textImeMultiLine',
    textLongMessage = 'textLongMessage',
    textMultiLine = 'textMultiLine',
    textNoSuggestions = 'textNoSuggestions',
    textPassword = 'textPassword',
    textPersonName = 'textPersonName',
    textPhonetic = 'textPhonetic',
    textPostalAddress = 'textPostalAddress',
    textShortMessage = 'textShortMessage',
    textUri = 'textUri',
    textVisiblePassword = 'textVisiblePassword',
    textWebEditText = 'textWebEditText',
    textWebEmailAddress = 'textWebEmailAddress',
    textWebPassword = 'textWebPassword',
    time = 'time'
  }
  export enum JustificationModeEnum {
    inter_word = 'inter_word',
    none = 'none'
  }
  export enum LayerTypeEnum {
    hardware = 'hardware',
    none = 'none',
    software = 'software'
  }
  export enum LayoutDirectionEnum {
    inherit = 'inherit',
    locale = 'locale',
    ltr = 'ltr',
    rtl = 'rtl'
  }
  export enum LayoutGravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum LayoutGravityFlagsEnum_ {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum LayoutHeightEnum {
    fill_parent = 'fill_parent',
    match_parent = 'match_parent',
    wrap_content = 'wrap_content'
  }
  export enum LayoutModeEnum {
    clipBounds = 'clipBounds',
    opticalBounds = 'opticalBounds'
  }
  export enum LayoutWidthEnum {
    fill_parent = 'fill_parent',
    match_parent = 'match_parent',
    wrap_content = 'wrap_content'
  }
  export interface LinearLayoutAttributes extends ViewGroupAttributes {
    baselineAligned?: boolean;
    baselineAlignedChildIndex?: number;
    divider?: number;
    dividerPadding?: string;
    gravity?: GravityFlagsEnum__[];
    measureWithLargestChild?: boolean;
    orientation?: OrientationEnum;
    showDividers?: ShowDividersFlagsEnum[];
  }
  export interface LinearLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: number | LayoutGravityFlagsEnum_[];
    layout_weight?: number;
  }
  export interface ListViewAttributes extends AbsListViewAttributes {
    divider?: string;
    dividerHeight?: string;
    footerDividersEnabled?: boolean;
    headerDividersEnabled?: boolean;
    overScrollFooter?: string;
    overScrollHeader?: string;
  }
  export enum MarqueeRepeatLimitEnum {
    marquee_forever = 'marquee_forever'
  }
  export interface MediaControllerAttributes extends FrameLayoutAttributes {
  }
  export interface MultiAutoCompleteTextViewAttributes extends AutoCompleteTextViewAttributes {
  }
  export enum NumColumnsEnum {
    auto_fit = 'auto_fit'
  }
  export interface NumberPickerAttributes extends LinearLayoutAttributes {
    internalMinHeight?: string;
    internalMinWidth?: string;
  }
  export enum OrientationEnum {
    horizontal = 'horizontal',
    vertical = 'vertical'
  }
  export enum OrientationEnum_ {
    horizontal = 'horizontal',
    vertical = 'vertical'
  }
  export enum OverScrollModeEnum {
    always = 'always',
    ifContentScrolls = 'ifContentScrolls',
    never = 'never'
  }
  export enum PersistentDrawingCacheFlagsEnum {
    all = 'all',
    animation = 'animation',
    none = 'none',
    scrolling = 'scrolling'
  }
  export enum ProgressBackgroundTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface ProgressBarAttributes extends ViewAttributes {
    indeterminate?: boolean;
    indeterminateDrawable?: string;
    indeterminateTint?: string;
    indeterminateTintMode?: IndeterminateTintModeEnum;
    interpolator?: string;
    max?: number;
    min?: number;
    progress?: number;
    progressBackgroundTint?: string;
    progressBackgroundTintMode?: ProgressBackgroundTintModeEnum;
    progressDrawable?: string;
    progressTint?: string;
    progressTintMode?: ProgressTintModeEnum;
    secondaryProgress?: number;
    secondaryProgressTint?: string;
    secondaryProgressTintMode?: SecondaryProgressTintModeEnum;
  }
  export enum ProgressTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface QuickContactBadgeAttributes extends ImageViewAttributes {
  }
  export interface RadioButtonAttributes extends CompoundButtonAttributes {
  }
  export interface RadioGroupAttributes extends LinearLayoutAttributes {
  }
  export interface RadioGroupLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
  }
  export interface RatingBarAttributes extends AbsSeekBarAttributes {
    isIndicator?: boolean;
    numStars?: number;
    rating?: number;
    stepSize?: number;
  }
  export interface RelativeLayoutAttributes extends ViewGroupAttributes {
    gravity?: GravityFlagsEnum_[];
    ignoreGravity?: string;
  }
  export interface RelativeLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }
  export enum ScaleTypeEnum {
    center = 'center',
    centerCrop = 'centerCrop',
    centerInside = 'centerInside',
    fitCenter = 'fitCenter',
    fitEnd = 'fitEnd',
    fitStart = 'fitStart',
    fitXY = 'fitXY',
    matrix = 'matrix'
  }
  export enum ScrollIndicatorsFlagsEnum {
    bottom = 'bottom',
    end = 'end',
    left = 'left',
    none = 'none',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export interface ScrollViewAttributes extends FrameLayoutAttributes {
    fillViewport?: boolean;
  }
  export enum ScrollbarStyleEnum {
    insideInset = 'insideInset',
    insideOverlay = 'insideOverlay',
    outsideInset = 'outsideInset',
    outsideOverlay = 'outsideOverlay'
  }
  export interface SearchViewAttributes extends LinearLayoutAttributes {
    iconifiedByDefault?: boolean;
    imeOptions?: number;
    inputType?: number;
    maxWidth?: string;
    queryHint?: string;
  }
  export enum SecondaryProgressTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface SeekBarAttributes extends AbsSeekBarAttributes {
  }
  export enum ShowDividersFlagsEnum {
    beginning = 'beginning',
    end = 'end',
    middle = 'middle',
    none = 'none'
  }
  export interface SpaceAttributes extends ViewAttributes {
  }
  export interface SpinnerAttributes extends AbsSpinnerAttributes {
    dropDownWidth?: number;
    gravity?: GravityFlagsEnum___[];
    popupBackground?: number;
  }
  export interface StackViewAttributes extends AdapterViewAnimatorAttributes {
  }
  export enum StretchModeEnum {
    columnWidth = 'columnWidth',
    none = 'none',
    spacingWidth = 'spacingWidth',
    spacingWidthUniform = 'spacingWidthUniform'
  }
  export interface SurfaceViewAttributes extends ViewAttributes {
  }
  export interface SwitchAttributes extends CompoundButtonAttributes {
    showText?: boolean;
    splitTrack?: boolean;
    switchMinWidth?: string;
    switchPadding?: string;
    switchTextAppearance?: string;
    textOff?: string;
    textOn?: string;
    thumb?: number;
    thumbTextPadding?: string;
    thumbTint?: number;
    thumbTintMode?: number;
    track?: string;
    trackTint?: string;
    trackTintMode?: TrackTintModeEnum;
  }
  export interface TabHostAttributes extends FrameLayoutAttributes {
  }
  export interface TabWidgetAttributes extends LinearLayoutAttributes {
    tabStripEnabled?: boolean;
    tabStripLeft?: string;
    tabStripRight?: string;
  }
  export interface TableLayoutAttributes extends LinearLayoutAttributes {
  }
  export interface TableLayoutLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
  }
  export interface TableRowAttributes extends LinearLayoutAttributes {
  }
  export interface TableRowLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
    TableRow_Cell_layout_column?: number;
    TableRow_Cell_layout_span?: number;
  }
  export enum TextAlignmentEnum {
    center = 'center',
    gravity = 'gravity',
    inherit = 'inherit',
    textEnd = 'textEnd',
    textStart = 'textStart',
    viewEnd = 'viewEnd',
    viewStart = 'viewStart'
  }
  export interface TextClockAttributes extends TextViewAttributes {
    format12Hour?: string;
    format24Hour?: string;
    timeZone?: string;
  }
  export enum TextDirectionEnum {
    anyRtl = 'anyRtl',
    firstStrong = 'firstStrong',
    firstStrongLtr = 'firstStrongLtr',
    firstStrongRtl = 'firstStrongRtl',
    inherit = 'inherit',
    locale = 'locale',
    ltr = 'ltr',
    rtl = 'rtl'
  }
  export interface TextSwitcherAttributes extends ViewSwitcherAttributes {
  }
  export interface TextViewAttributes extends ViewAttributes {
    View_clickable?: boolean;
    View_longClickable?: boolean;
    autoLink?: AutoLinkFlagsEnum[];
    autoSizeTextType?: AutoSizeTextTypeEnum;
    breakStrategy?: BreakStrategyEnum;
    cursorVisible?: boolean;
    drawableBottom?: string;
    drawableLeft?: string;
    drawablePadding?: string;
    drawableRight?: string;
    drawableTint?: string;
    drawableTintMode?: DrawableTintModeEnum;
    drawableTop?: string;
    editorExtras?: string;
    elegantTextHeight?: boolean;
    ellipsize?: EllipsizeEnum;
    ems?: number;
    enabled?: boolean;
    fallbackLineSpacing?: boolean;
    firstBaselineToTopHeight?: string;
    fontFeatureSettings?: string;
    freezesText?: boolean;
    gravity?: GravityFlagsEnum[];
    height?: string;
    hint?: string;
    hyphenationFrequency?: HyphenationFrequencyEnum;
    imeOptions?: ImeOptionsFlagsEnum[];
    includeFontPadding?: boolean;
    inputType?: InputTypeFlagsEnum[];
    justificationMode?: JustificationModeEnum;
    lastBaselineToBottomHeight?: string;
    letterSpacing?: number;
    lineHeight?: string;
    lines?: number;
    linksClickable?: boolean;
    marqueeRepeatLimit?: number | MarqueeRepeatLimitEnum;
    maxEms?: number;
    maxHeight?: string;
    maxLines?: number;
    maxWidth?: string;
    minEms?: number;
    minLines?: number;
    privateImeOptions?: string;
    shadowColor?: string;
    shadowDx?: number;
    shadowDy?: number;
    shadowRadius?: number;
    text?: string;
    textAllCaps?: boolean;
    textColor?: string;
    textColorHighlight?: string;
    textColorHint?: string;
    textColorLink?: string;
    textIsSelectable?: boolean;
    textScaleX?: number;
    textSize?: string;
    width?: string;
  }
  export interface TextureViewAttributes extends ViewAttributes {
  }
  export enum ThumbTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export enum TickMarkTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface TimePickerAttributes extends FrameLayoutAttributes {
  }
  export interface ToggleButtonAttributes extends CompoundButtonAttributes {
    textOff?: string;
    textOn?: string;
  }
  export interface ToolbarAttributes extends ViewGroupAttributes {
    contentInsetEnd?: string;
    contentInsetEndWithActions?: string;
    contentInsetLeft?: string;
    contentInsetRight?: string;
    contentInsetStart?: string;
    contentInsetStartWithNavigation?: string;
    logo?: number;
    logoDescription?: string;
    navigationContentDescription?: string;
    navigationIcon?: string;
    popupTheme?: number;
    subtitle?: string;
    subtitleTextColor?: string;
    title?: string;
    titleMargin?: string;
    titleMarginBottom?: string;
    titleMarginEnd?: string;
    titleMarginStart?: string;
    titleMarginTop?: string;
    titleTextColor?: string;
  }
  export interface ToolbarLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
  }
  export enum TrackTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export enum TranscriptModeEnum {
    alwaysScroll = 'alwaysScroll',
    disabled = 'disabled',
    normal = 'normal'
  }
  export enum VerticalScrollbarPositionEnum {
    defaultPosition = 'defaultPosition',
    left = 'left',
    right = 'right'
  }
  export interface VideoViewAttributes extends SurfaceViewAttributes {
  }
  export interface ViewAnimatorAttributes extends FrameLayoutAttributes {
    FrameLayout_measureAllChildren?: boolean;
    animateFirstView?: boolean;
    inAnimation?: string;
    outAnimation?: string;
  }
  export interface ViewAttributes {
    accessibilityHeading?: boolean;
    accessibilityLiveRegion?: number | AccessibilityLiveRegionEnum;
    accessibilityPaneTitle?: string;
    accessibilityTraversalAfter?: number;
    accessibilityTraversalBefore?: number;
    alpha?: number;
    autofillHints?: string;
    background?: string;
    backgroundTint?: string;
    backgroundTintMode?: BackgroundTintModeEnum;
    clickable?: boolean;
    contentDescription?: string;
    contextClickable?: boolean;
    defaultFocusHighlightEnabled?: boolean;
    drawingCacheQuality?: DrawingCacheQualityEnum;
    elevation?: string;
    fadeScrollbars?: boolean;
    filterTouchesWhenObscured?: boolean;
    fitsSystemWindows?: boolean;
    focusable?: boolean | FocusableEnum;
    focusableInTouchMode?: boolean;
    focusedByDefault?: boolean;
    forceHasOverlappingRendering?: boolean;
    foreground?: string;
    foregroundGravity?: ForegroundGravityFlagsEnum[];
    foregroundTint?: string;
    foregroundTintMode?: ForegroundTintModeEnum;
    hapticFeedbackEnabled?: boolean;
    id?: string;
    importantForAccessibility?: number | ImportantForAccessibilityEnum;
    importantForAutofill?: ImportantForAutofillFlagsEnum[];
    isScrollContainer?: boolean;
    keepScreenOn?: boolean;
    keyboardNavigationCluster?: boolean;
    labelFor?: string;
    layerType?: LayerTypeEnum;
    layoutDirection?: LayoutDirectionEnum;
    longClickable?: boolean;
    minHeight?: string;
    minWidth?: string;
    nestedScrollingEnabled?: boolean;
    nextClusterForward?: string;
    nextFocusDown?: string;
    nextFocusForward?: string;
    nextFocusLeft?: string;
    nextFocusRight?: string;
    nextFocusUp?: string;
    outlineAmbientShadowColor?: string;
    outlineSpotShadowColor?: string;
    overScrollMode?: OverScrollModeEnum;
    paddingBottom?: string;
    paddingEnd?: string;
    paddingLeft?: string;
    paddingRight?: string;
    paddingStart?: string;
    paddingTop?: string;
    rotation?: number;
    rotationX?: number;
    rotationY?: number;
    saveEnabled?: boolean;
    scaleX?: number;
    scaleY?: number;
    screenReaderFocusable?: boolean;
    scrollIndicators?: ScrollIndicatorsFlagsEnum[];
    scrollX?: string;
    scrollY?: string;
    scrollbarDefaultDelayBeforeFade?: number;
    scrollbarFadeDuration?: number;
    scrollbarSize?: string;
    scrollbarStyle?: ScrollbarStyleEnum;
    soundEffectsEnabled?: boolean;
    tag?: string;
    textAlignment?: number | TextAlignmentEnum;
    textDirection?: number | TextDirectionEnum;
    tooltipText?: string;
    transformPivotX?: string;
    transformPivotY?: string;
    transitionName?: string;
    translationX?: string;
    translationY?: string;
    translationZ?: string;
    verticalScrollbarPosition?: VerticalScrollbarPositionEnum;
    visibility?: VisibilityEnum;
  }
  export interface ViewFlipperAttributes extends ViewAnimatorAttributes {
    autoStart?: boolean;
    flipInterval?: number;
  }
  export interface ViewGroupAttributes extends ViewAttributes {
    addStatesFromChildren?: boolean;
    alwaysDrawnWithCache?: boolean;
    animateLayoutChanges?: boolean;
    animationCache?: boolean;
    clipChildren?: boolean;
    clipToPadding?: boolean;
    layoutMode?: LayoutModeEnum;
    persistentDrawingCache?: PersistentDrawingCacheFlagsEnum[];
    splitMotionEvents?: boolean;
    touchscreenBlocksFocus?: boolean;
    transitionGroup?: boolean;
  }
  export interface ViewGroupLayoutParamsAttributes {
    layout_height?: string | LayoutHeightEnum;
    layout_width?: string | LayoutWidthEnum;
  }
  export interface ViewGroupMarginLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
    layout_marginBottom?: string;
    layout_marginEnd?: string;
    layout_marginLeft?: string;
    layout_marginRight?: string;
    layout_marginStart?: string;
    layout_marginTop?: string;
  }
  export interface ViewStubAttributes extends ViewAttributes {
    inflatedId?: string;
    layout?: string;
  }
  export interface ViewSwitcherAttributes extends ViewAnimatorAttributes {
  }
  export enum VisibilityEnum {
    gone = 'gone',
    invisible = 'invisible',
    visible = 'visible'
  }
  export interface ZoomControlsAttributes extends LinearLayoutAttributes {
  }
}
// elements
export const AdapterViewAnimator = (
  attributes?: MainTypes.AdapterViewAnimatorAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AdapterViewAnimatorAttributes, LayoutParamAttributes> => {
  return element('adapterViewAnimator', attributes, children);
};
export const FrameLayout = (
  attributes?: MainTypes.FrameLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.FrameLayoutAttributes, LayoutParamAttributes> => {
  return element('frameLayout', attributes, children);
};
export const View = (
  attributes?: MainTypes.ViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ViewAttributes, LayoutParamAttributes> => {
  return element('view', attributes, children);
};
export const SearchView = (
  attributes?: MainTypes.SearchViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.SearchViewAttributes, LayoutParamAttributes> => {
  return element('searchView', attributes, children);
};
export const TableRow = (
  attributes?: MainTypes.TableRowAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TableRowAttributes, LayoutParamAttributes> => {
  return element('tableRow', attributes, children);
};
export const RelativeLayout = (
  attributes?: MainTypes.RelativeLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.RelativeLayoutAttributes, LayoutParamAttributes> => {
  return element('relativeLayout', attributes, children);
};
export const ImageSwitcher = (
  attributes?: MainTypes.ImageSwitcherAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ImageSwitcherAttributes, LayoutParamAttributes> => {
  return element('imageSwitcher', attributes, children);
};
export const RadioGroup = (
  attributes?: MainTypes.RadioGroupAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.RadioGroupAttributes, LayoutParamAttributes> => {
  return element('radioGroup', attributes, children);
};
export const Toolbar = (
  attributes?: MainTypes.ToolbarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ToolbarAttributes, LayoutParamAttributes> => {
  return element('toolbar', attributes, children);
};
export const ListView = (
  attributes?: MainTypes.ListViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ListViewAttributes, LayoutParamAttributes> => {
  return element('listView', attributes, children);
};
export const Spinner = (
  attributes?: MainTypes.SpinnerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.SpinnerAttributes, LayoutParamAttributes> => {
  return element('spinner', attributes, children);
};
export const ScrollView = (
  attributes?: MainTypes.ScrollViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ScrollViewAttributes, LayoutParamAttributes> => {
  return element('scrollView', attributes, children);
};
export const ViewFlipper = (
  attributes?: MainTypes.ViewFlipperAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ViewFlipperAttributes, LayoutParamAttributes> => {
  return element('viewFlipper', attributes, children);
};
export const ViewSwitcher = (
  attributes?: MainTypes.ViewSwitcherAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ViewSwitcherAttributes, LayoutParamAttributes> => {
  return element('viewSwitcher', attributes, children);
};
export const DatePicker = (
  attributes?: MainTypes.DatePickerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.DatePickerAttributes, LayoutParamAttributes> => {
  return element('datePicker', attributes, children);
};
export const StackView = (
  attributes?: MainTypes.StackViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.StackViewAttributes, LayoutParamAttributes> => {
  return element('stackView', attributes, children);
};
export const TimePicker = (
  attributes?: MainTypes.TimePickerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TimePickerAttributes, LayoutParamAttributes> => {
  return element('timePicker', attributes, children);
};
export const MediaController = (
  attributes?: MainTypes.MediaControllerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.MediaControllerAttributes, LayoutParamAttributes> => {
  return element('mediaController', attributes, children);
};
export const ActionMenuView = (
  attributes?: MainTypes.ActionMenuViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ActionMenuViewAttributes, LayoutParamAttributes> => {
  return element('actionMenuView', attributes, children);
};
export const ZoomControls = (
  attributes?: MainTypes.ZoomControlsAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ZoomControlsAttributes, LayoutParamAttributes> => {
  return element('zoomControls', attributes, children);
};
export const AdapterView = (
  attributes?: MainTypes.AdapterViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AdapterViewAttributes, LayoutParamAttributes> => {
  return element('adapterView', attributes, children);
};
export const ViewAnimator = (
  attributes?: MainTypes.ViewAnimatorAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ViewAnimatorAttributes, LayoutParamAttributes> => {
  return element('viewAnimator', attributes, children);
};
export const HorizontalScrollView = (
  attributes?: MainTypes.HorizontalScrollViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.HorizontalScrollViewAttributes, LayoutParamAttributes> => {
  return element('horizontalScrollView', attributes, children);
};
export const CalendarView = (
  attributes?: MainTypes.CalendarViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.CalendarViewAttributes, LayoutParamAttributes> => {
  return element('calendarView', attributes, children);
};
export const AbsListView = (
  attributes?: MainTypes.AbsListViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AbsListViewAttributes, LayoutParamAttributes> => {
  return element('absListView', attributes, children);
};
export const LinearLayout = (
  attributes?: MainTypes.LinearLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.LinearLayoutAttributes, LayoutParamAttributes> => {
  return element('linearLayout', attributes, children);
};
export const NumberPicker = (
  attributes?: MainTypes.NumberPickerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.NumberPickerAttributes, LayoutParamAttributes> => {
  return element('numberPicker', attributes, children);
};
export const AdapterViewFlipper = (
  attributes?: MainTypes.AdapterViewFlipperAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AdapterViewFlipperAttributes, LayoutParamAttributes> => {
  return element('adapterViewFlipper', attributes, children);
};
export const AbsSpinner = (
  attributes?: MainTypes.AbsSpinnerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AbsSpinnerAttributes, LayoutParamAttributes> => {
  return element('absSpinner', attributes, children);
};
export const ViewGroup = (
  attributes?: MainTypes.ViewGroupAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ViewGroupAttributes, LayoutParamAttributes> => {
  return element('viewGroup', attributes, children);
};
export const TextSwitcher = (
  attributes?: MainTypes.TextSwitcherAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TextSwitcherAttributes, LayoutParamAttributes> => {
  return element('textSwitcher', attributes, children);
};
export const ExpandableListView = (
  attributes?: MainTypes.ExpandableListViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ExpandableListViewAttributes, LayoutParamAttributes> => {
  return element('expandableListView', attributes, children);
};
export const GridView = (
  attributes?: MainTypes.GridViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.GridViewAttributes, LayoutParamAttributes> => {
  return element('gridView', attributes, children);
};
export const TableLayout = (
  attributes?: MainTypes.TableLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TableLayoutAttributes, LayoutParamAttributes> => {
  return element('tableLayout', attributes, children);
};
export const TabHost = (
  attributes?: MainTypes.TabHostAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TabHostAttributes, LayoutParamAttributes> => {
  return element('tabHost', attributes, children);
};
export const GridLayout = (
  attributes?: MainTypes.GridLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.GridLayoutAttributes, LayoutParamAttributes> => {
  return element('gridLayout', attributes, children);
};
export const TabWidget = (
  attributes?: MainTypes.TabWidgetAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TabWidgetAttributes, LayoutParamAttributes> => {
  return element('tabWidget', attributes, children);
};
export const Space = (
  attributes?: MainTypes.SpaceAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.SpaceAttributes, LayoutParamAttributes> => {
  return element('space', attributes, children);
};
export const MultiAutoCompleteTextView = (
  attributes?: MainTypes.MultiAutoCompleteTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.MultiAutoCompleteTextViewAttributes, LayoutParamAttributes> => {
  return element('multiAutoCompleteTextView', attributes, children);
};
export const QuickContactBadge = (
  attributes?: MainTypes.QuickContactBadgeAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.QuickContactBadgeAttributes, LayoutParamAttributes> => {
  return element('quickContactBadge', attributes, children);
};
export const EditText = (
  attributes?: MainTypes.EditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.EditTextAttributes, LayoutParamAttributes> => {
  return element('editText', attributes, children);
};
export const SurfaceView = (
  attributes?: MainTypes.SurfaceViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.SurfaceViewAttributes, LayoutParamAttributes> => {
  return element('surfaceView', attributes, children);
};
export const RatingBar = (
  attributes?: MainTypes.RatingBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.RatingBarAttributes, LayoutParamAttributes> => {
  return element('ratingBar', attributes, children);
};
export const ImageView = (
  attributes?: MainTypes.ImageViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ImageViewAttributes, LayoutParamAttributes> => {
  return element('imageView', attributes, children);
};
export const AbsSeekBar = (
  attributes?: MainTypes.AbsSeekBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AbsSeekBarAttributes, LayoutParamAttributes> => {
  return element('absSeekBar', attributes, children);
};
export const ViewStub = (
  attributes?: MainTypes.ViewStubAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ViewStubAttributes, LayoutParamAttributes> => {
  return element('viewStub', attributes, children);
};
export const CheckedTextView = (
  attributes?: MainTypes.CheckedTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.CheckedTextViewAttributes, LayoutParamAttributes> => {
  return element('checkedTextView', attributes, children);
};
export const TextureView = (
  attributes?: MainTypes.TextureViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TextureViewAttributes, LayoutParamAttributes> => {
  return element('textureView', attributes, children);
};
export const ProgressBar = (
  attributes?: MainTypes.ProgressBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ProgressBarAttributes, LayoutParamAttributes> => {
  return element('progressBar', attributes, children);
};
export const TextView = (
  attributes?: MainTypes.TextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TextViewAttributes, LayoutParamAttributes> => {
  return element('textView', attributes, children);
};
export const CheckBox = (
  attributes?: MainTypes.CheckBoxAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.CheckBoxAttributes, LayoutParamAttributes> => {
  return element('checkBox', attributes, children);
};
export const Switch = (
  attributes?: MainTypes.SwitchAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.SwitchAttributes, LayoutParamAttributes> => {
  return element('switch', attributes, children);
};
export const RadioButton = (
  attributes?: MainTypes.RadioButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.RadioButtonAttributes, LayoutParamAttributes> => {
  return element('radioButton', attributes, children);
};
export const SeekBar = (
  attributes?: MainTypes.SeekBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.SeekBarAttributes, LayoutParamAttributes> => {
  return element('seekBar', attributes, children);
};
export const CompoundButton = (
  attributes?: MainTypes.CompoundButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.CompoundButtonAttributes, LayoutParamAttributes> => {
  return element('compoundButton', attributes, children);
};
export const ToggleButton = (
  attributes?: MainTypes.ToggleButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ToggleButtonAttributes, LayoutParamAttributes> => {
  return element('toggleButton', attributes, children);
};
export const TextClock = (
  attributes?: MainTypes.TextClockAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.TextClockAttributes, LayoutParamAttributes> => {
  return element('textClock', attributes, children);
};
export const ImageButton = (
  attributes?: MainTypes.ImageButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ImageButtonAttributes, LayoutParamAttributes> => {
  return element('imageButton', attributes, children);
};
export const Chronometer = (
  attributes?: MainTypes.ChronometerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ChronometerAttributes, LayoutParamAttributes> => {
  return element('chronometer', attributes, children);
};
export const VideoView = (
  attributes?: MainTypes.VideoViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.VideoViewAttributes, LayoutParamAttributes> => {
  return element('videoView', attributes, children);
};
export const AutoCompleteTextView = (
  attributes?: MainTypes.AutoCompleteTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.AutoCompleteTextViewAttributes, LayoutParamAttributes> => {
  return element('autoCompleteTextView', attributes, children);
};
export const Button = (
  attributes?: MainTypes.ButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<MainTypes.ButtonAttributes, LayoutParamAttributes> => {
  return element('button', attributes, children);
};