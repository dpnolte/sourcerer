/* generated @ 2018-11-10T13:55:09.502 */

declare namespace MainTypes {
  export interface AbsListViewAttributes extends AdapterViewAttributes {
    cacheColorHint?: string;
    choiceMode?: ChoiceModeEnum;
    drawSelectorOnTop?: boolean;
    fastScrollAlwaysVisible?: boolean;
    fastScrollEnabled?: boolean;
    fastScrollStyle?: string;
    listSelector?: any;
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

  export enum AlignmentModeEnum { 'alignBounds', 'alignMargins' }

  export interface AutoCompleteTextViewAttributes extends EditTextAttributes {
    completionHint?: string;
    completionThreshold?: number;
    dropDownAnchor?: string;
    dropDownHeight?: any;
    dropDownHorizontalOffset?: number;
    dropDownVerticalOffset?: number;
    dropDownWidth?: any;
  }

  export enum AutoSizeTextTypeEnum { 'none', 'uniform' }

  export enum BackgroundTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export enum BreakStrategyEnum { 'simple', 'high_quality', 'balanced' }

  export interface ButtonAttributes extends TextViewAttributes {
  }

  export enum ButtonTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

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

  export enum CheckMarkTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export interface CheckedTextViewAttributes extends TextViewAttributes {
    checkMark?: string;
    checkMarkTint?: string;
    checkMarkTintMode?: CheckMarkTintModeEnum;
    checked?: boolean;
  }

  export enum ChoiceModeEnum { 'none', 'singleChoice', 'multipleChoice', 'multipleChoiceModal' }

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

  export enum DrawableTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export enum DrawingCacheQualityEnum { 'auto', 'low', 'high' }

  export interface EditTextAttributes extends TextViewAttributes {
  }

  export enum EllipsizeEnum { 'none', 'start', 'middle', 'end', 'marquee' }

  export interface ExpandableListViewAttributes extends ListViewAttributes {
    childDivider?: any;
    childIndicator?: string;
    groupIndicator?: string;
  }

  export enum ForegroundTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export interface FrameLayoutAttributes extends ViewGroupAttributes {
    measureAllChildren?: boolean;
  }

  export interface FrameLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: string;
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
    gravity?: string;
    horizontalSpacing?: string;
    numColumns?: any;
    stretchMode?: StretchModeEnum;
    verticalSpacing?: string;
  }

  export interface HorizontalScrollViewAttributes extends FrameLayoutAttributes {
    fillViewport?: boolean;
  }

  export enum HyphenationFrequencyEnum { 'none', 'normal', 'full' }

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
    src?: any;
    tint?: string;
    tintMode?: number;
  }

  export enum IndeterminateTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export enum JustificationModeEnum { 'none', 'inter_word' }

  export enum LayerTypeEnum { 'none', 'software', 'hardware' }

  export enum LayoutDirectionEnum { 'ltr', 'rtl', 'inherit', 'locale' }

  export enum LayoutModeEnum { 'clipBounds', 'opticalBounds' }

  export interface LinearLayoutAttributes extends ViewGroupAttributes {
    baselineAligned?: boolean;
    baselineAlignedChildIndex?: number;
    divider?: number;
    dividerPadding?: string;
    gravity?: string;
    measureWithLargestChild?: boolean;
    orientation?: OrientationEnum;
    showDividers?: string;
  }

  export interface LinearLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: any;
    layout_weight?: number;
  }

  export interface ListViewAttributes extends AbsListViewAttributes {
    divider?: any;
    dividerHeight?: string;
    footerDividersEnabled?: boolean;
    headerDividersEnabled?: boolean;
    overScrollFooter?: any;
    overScrollHeader?: any;
  }

  export interface MediaControllerAttributes extends FrameLayoutAttributes {
  }

  export interface MultiAutoCompleteTextViewAttributes extends AutoCompleteTextViewAttributes {
  }

  export interface NumberPickerAttributes extends LinearLayoutAttributes {
    internalMinHeight?: string;
    internalMinWidth?: string;
  }

  export enum OrientationEnum { 'horizontal', 'vertical' }

  export enum OrientationEnum_ { 'horizontal', 'vertical' }

  export enum OverScrollModeEnum { 'always', 'ifContentScrolls', 'never' }

  export enum ProgressBackgroundTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

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

  export enum ProgressTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

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
    gravity?: string;
    ignoreGravity?: string;
  }

  export interface RelativeLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }

  export enum ScaleTypeEnum { 'matrix', 'fitXY', 'fitStart', 'fitCenter', 'fitEnd', 'center', 'centerCrop', 'centerInside' }

  export interface ScrollViewAttributes extends FrameLayoutAttributes {
    fillViewport?: boolean;
  }

  export enum ScrollbarStyleEnum { 'insideOverlay', 'insideInset', 'outsideOverlay', 'outsideInset' }

  export interface SearchViewAttributes extends LinearLayoutAttributes {
    iconifiedByDefault?: boolean;
    imeOptions?: number;
    inputType?: number;
    maxWidth?: string;
    queryHint?: string;
  }

  export enum SecondaryProgressTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export interface SeekBarAttributes extends AbsSeekBarAttributes {
  }

  export interface SpaceAttributes extends ViewAttributes {
  }

  export interface SpinnerAttributes extends AbsSpinnerAttributes {
    dropDownWidth?: number;
    gravity?: string;
    popupBackground?: number;
  }

  export interface StackViewAttributes extends AdapterViewAnimatorAttributes {
  }

  export enum StretchModeEnum { 'none', 'spacingWidth', 'columnWidth', 'spacingWidthUniform' }

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

  export interface TextClockAttributes extends TextViewAttributes {
    format12Hour?: string;
    format24Hour?: string;
    timeZone?: string;
  }

  export interface TextSwitcherAttributes extends ViewSwitcherAttributes {
  }

  export interface TextViewAttributes extends ViewAttributes {
    View_clickable?: boolean;
    View_longClickable?: boolean;
    autoLink?: string;
    autoSizeTextType?: AutoSizeTextTypeEnum;
    breakStrategy?: BreakStrategyEnum;
    cursorVisible?: boolean;
    drawableBottom?: any;
    drawableLeft?: any;
    drawablePadding?: string;
    drawableRight?: any;
    drawableTint?: string;
    drawableTintMode?: DrawableTintModeEnum;
    drawableTop?: any;
    editorExtras?: string;
    elegantTextHeight?: boolean;
    ellipsize?: EllipsizeEnum;
    ems?: number;
    enabled?: boolean;
    fallbackLineSpacing?: boolean;
    firstBaselineToTopHeight?: string;
    fontFeatureSettings?: string;
    freezesText?: boolean;
    gravity?: string;
    height?: string;
    hint?: string;
    hyphenationFrequency?: HyphenationFrequencyEnum;
    imeOptions?: string;
    includeFontPadding?: boolean;
    inputType?: string;
    justificationMode?: JustificationModeEnum;
    lastBaselineToBottomHeight?: string;
    letterSpacing?: number;
    lineHeight?: string;
    lines?: number;
    linksClickable?: boolean;
    marqueeRepeatLimit?: any;
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
    textColor?: any;
    textColorHighlight?: string;
    textColorHint?: any;
    textColorLink?: any;
    textIsSelectable?: boolean;
    textScaleX?: number;
    textSize?: string;
    width?: string;
  }

  export interface TextureViewAttributes extends ViewAttributes {
  }

  export enum ThumbTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export enum TickMarkTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

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

  export enum TrackTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  export enum TranscriptModeEnum { 'disabled', 'normal', 'alwaysScroll' }

  export enum VerticalScrollbarPositionEnum { 'defaultPosition', 'left', 'right' }

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
    accessibilityLiveRegion?: any;
    accessibilityPaneTitle?: string;
    accessibilityTraversalAfter?: number;
    accessibilityTraversalBefore?: number;
    alpha?: number;
    autofillHints?: any;
    background?: any;
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
    focusable?: any;
    focusableInTouchMode?: boolean;
    focusedByDefault?: boolean;
    forceHasOverlappingRendering?: boolean;
    foreground?: any;
    foregroundGravity?: string;
    foregroundTint?: string;
    foregroundTintMode?: ForegroundTintModeEnum;
    hapticFeedbackEnabled?: boolean;
    id?: string;
    importantForAccessibility?: any;
    importantForAutofill?: string;
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
    scrollIndicators?: string;
    scrollX?: string;
    scrollY?: string;
    scrollbarDefaultDelayBeforeFade?: number;
    scrollbarFadeDuration?: number;
    scrollbarSize?: string;
    scrollbarStyle?: ScrollbarStyleEnum;
    soundEffectsEnabled?: boolean;
    tag?: string;
    textAlignment?: any;
    textDirection?: any;
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
    persistentDrawingCache?: string;
    splitMotionEvents?: boolean;
    touchscreenBlocksFocus?: boolean;
    transitionGroup?: boolean;
  }

  export interface ViewGroupLayoutParamsAttributes {
    layout_height?: any;
    layout_width?: any;
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

  export enum VisibilityEnum { 'visible', 'invisible', 'gone' }

  export interface ZoomControlsAttributes extends LinearLayoutAttributes {
  }
}
